package bai6;
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
                System.out.println("\n===== NGAN HANG RMI =====");
                System.out.println("1. Xem so du");
                System.out.println("2. Nap tien");
                System.out.println("3. Rut tien");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");

                choice = Integer.parseInt(sc.nextLine());

                double amount;

                switch (choice) {
                    case 1:
                        System.out.printf(
                                "So du: %,.0f VND%n",
                                service.getBalance());
                        break;

                    case 2:
                        System.out.print("Nhap so tien nap: ");
                        amount = Double.parseDouble(sc.nextLine());

                        if (Double.isFinite(amount) && amount > 0) {
                            service.deposit(amount);
                            System.out.println("Nap tien thanh cong!");
                        } else {
                            System.out.println("So tien khong hop le!");
                        }
                        break;

                    case 3:
                        System.out.print("Nhap so tien rut: ");
                        amount = Double.parseDouble(sc.nextLine());

                        if (service.withdraw(amount)) {
                            System.out.println("Rut tien thanh cong!");
                        } else {
                            System.out.println(
                                    "So tien khong hop le hoac khong du so du!");
                        }
                        break;

                    case 0:
                        System.out.println("Tam biet!");
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