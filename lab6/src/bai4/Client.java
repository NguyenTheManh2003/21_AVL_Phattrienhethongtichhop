package bai4;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            Registry registry =
                    LocateRegistry.getRegistry("localhost", 1099);

            Service service =
                    (Service) registry.lookup("Service");

            int choice;

            do {
                System.out.println("\n===== QUAN LY DANH BA =====");
                System.out.println("1. Them lien he");
                System.out.println("2. Tim lien he");
                System.out.println("3. Sua lien he");
                System.out.println("4. Xoa lien he");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");

                choice = Integer.parseInt(sc.nextLine());

                String name, phone;

                switch (choice) {
                    case 1:
                        System.out.print("Nhap ten: ");
                        name = sc.nextLine();

                        System.out.print("Nhap SDT: ");
                        phone = sc.nextLine();

                        service.addContact(name, phone);
                        System.out.println("Them thanh cong!");
                        break;

                    case 2:
                        System.out.print("Nhap ten can tim: ");
                        name = sc.nextLine();

                        System.out.println("Ket qua: "
                                + service.findContact(name));
                        break;

                    case 3:
                        System.out.print("Nhap ten can sua: ");
                        name = sc.nextLine();

                        System.out.print("Nhap SDT moi: ");
                        phone = sc.nextLine();

                        if (service.updateContact(name, phone)) {
                            System.out.println("Sua thanh cong!");
                        } else {
                            System.out.println("Khong tim thay!");
                        }
                        break;

                    case 4:
                        System.out.print("Nhap ten can xoa: ");
                        name = sc.nextLine();

                        if (service.deleteContact(name)) {
                            System.out.println("Xoa thanh cong!");
                        } else {
                            System.out.println("Khong tim thay!");
                        }
                        break;

                    case 0:
                        System.out.println("Thoat chuong trinh!");
                        break;

                    default:
                        System.out.println("Lua chon khong hop le!");
                }

            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}