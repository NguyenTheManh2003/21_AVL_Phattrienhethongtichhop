package bai9;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class Client extends UnicastRemoteObject
        implements AuctionCallback {

    public Client() throws RemoteException {
        super();
    }

    @Override
    public void notifyBid(String message) {
        System.out.println(
                "\n[THONG BAO] " + message);
        System.out.print("Chon: ");
    }

    public static void main(String[] args) {
        AuctionService service = null;
        Client callback = null;

        try {
            Scanner sc = new Scanner(System.in);

            Registry registry =
                    LocateRegistry.getRegistry(
                            "localhost", 1099);

            service = (AuctionService)
                    registry.lookup("AuctionService");

            callback = new Client();
            service.registerCallback(callback);

            System.out.println(
                    "Da ket noi he thong dau gia!");

            System.out.print("Nhap ten cua ban: ");
            String username = sc.nextLine().trim();

            while (username.isEmpty()) {
                System.out.print("Nhap lai ten: ");
                username = sc.nextLine().trim();
            }

            int choice;

            do {
                System.out.println(
                        "\n===== HE THONG DAU GIA =====");

                System.out.println("1. Them san pham (Seller)");
                System.out.println("2. Xem danh sach san pham");
                System.out.println("3. Dat gia (Bidder)");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Ten san pham: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Gia khoi diem: ");
                        double startPrice =
                                Double.parseDouble(sc.nextLine());

                        if (service.addProduct(name, startPrice)) {
                            System.out.println(
                                    "Them san pham thanh cong!");
                        } else {
                            System.out.println(
                                    "Them that bai! Kiem tra ten va gia.");
                        }
                        break;

                    case 2:
                        List<Product> products =
                                service.getProducts();

                        if (products.isEmpty()) {
                            System.out.println(
                                    "Chua co san pham nao.");
                        } else {
                            System.out.println(
                                    "\nDANH SACH SAN PHAM:");

                            for (Product p : products) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Ten san pham: ");
                        String productName =
                                sc.nextLine().trim();

                        System.out.print("Gia muon dat: ");
                        double bidPrice =
                                Double.parseDouble(sc.nextLine());

                        if (service.placeBid(
                                productName,
                                username,
                                bidPrice)) {

                            System.out.println(
                                    "Dat gia thanh cong!");
                        } else {
                            System.out.println(
                                    "Dat gia that bai!");
                            System.out.println(
                                    "Gia phai cao hon gia hien tai.");
                        }
                        break;

                    case 0:
                        System.out.println(
                                "Dang thoat he thong...");
                        break;

                    default:
                        System.out.println(
                                "Lua chon khong hop le!");
                }

            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (service != null && callback != null) {
                try {
                    service.unregisterCallback(callback);
                } catch (Exception ignored) {
                }
            }

            if (callback != null) {
                try {
                    UnicastRemoteObject.unexportObject(
                            callback, true);
                } catch (Exception ignored) {
                }
            }

            System.exit(0);
        }
    }
}