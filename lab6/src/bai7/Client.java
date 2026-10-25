package bai7;
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
                System.out.println("\n===== TINH DIEN TICH =====");
                System.out.println("1. Hinh chu nhat");
                System.out.println("2. Hinh tron");
                System.out.println("3. Hinh tam giac");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");

                choice = Integer.parseInt(sc.nextLine());

                double a, b, result;

                switch (choice) {
                    case 1:
                        System.out.print("Nhap chieu dai: ");
                        a = Double.parseDouble(sc.nextLine());

                        System.out.print("Nhap chieu rong: ");
                        b = Double.parseDouble(sc.nextLine());

                        result = service.rectangleArea(a, b);

                        System.out.printf(
                                "Dien tich HCN = %.2f%n", result);
                        break;

                    case 2:
                        System.out.print("Nhap ban kinh: ");
                        a = Double.parseDouble(sc.nextLine());

                        result = service.circleArea(a);

                        System.out.printf(
                                "Dien tich hinh tron = %.2f%n",
                                result);
                        break;

                    case 3:
                        System.out.print("Nhap day: ");
                        a = Double.parseDouble(sc.nextLine());

                        System.out.print("Nhap chieu cao: ");
                        b = Double.parseDouble(sc.nextLine());

                        result = service.triangleArea(a, b);

                        System.out.printf(
                                "Dien tich tam giac = %.2f%n",
                                result);
                        break;

                    case 0:
                        System.out.println("Thoat!");
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