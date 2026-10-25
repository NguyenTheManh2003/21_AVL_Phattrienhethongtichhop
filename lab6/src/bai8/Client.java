package bai8;
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
                System.out.println("\n===== DAT VE MAY BAY =====");
                System.out.println("1. Kiem tra ghe trong");
                System.out.println("2. Dat ve");
                System.out.println("3. Huy ve");
                System.out.println("0. Thoat");

                System.out.println("\nDanh sach chuyen bay:");
                System.out.println("VN101 | VN202 | VN303");

                System.out.print("Chon: ");
                choice = Integer.parseInt(sc.nextLine());

                String flight;
                int seats;

                switch (choice) {
                    case 1:
                        System.out.print("Nhap ma chuyen bay: ");
                        flight = sc.nextLine().trim().toUpperCase();

                        int available =
                                service.availableSeats(flight);

                        if (available == -1) {
                            System.out.println(
                                    "Khong tim thay chuyen bay!");
                        } else {
                            System.out.println(
                                    "So ghe trong: " + available);
                        }
                        break;

                    case 2:
                        System.out.print("Nhap ma chuyen bay: ");
                        flight = sc.nextLine().trim().toUpperCase();

                        System.out.print("Nhap so ghe muon dat: ");
                        seats = Integer.parseInt(sc.nextLine());

                        if (service.bookTicket(flight, seats)) {
                            System.out.println("Dat ve thanh cong!");
                        } else {
                            System.out.println(
                                    "Dat ve that bai!");
                        }
                        break;

                    case 3:
                        System.out.print("Nhap ma chuyen bay: ");
                        flight = sc.nextLine().trim().toUpperCase();

                        System.out.print("Nhap so ghe muon huy: ");
                        seats = Integer.parseInt(sc.nextLine());

                        if (service.cancelBooking(flight, seats)) {
                            System.out.println("Huy ve thanh cong!");
                        } else {
                            System.out.println(
                                    "Huy ve that bai!");
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