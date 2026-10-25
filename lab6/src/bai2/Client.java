package bai2;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Provider;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            Registry registry =
                    LocateRegistry.getRegistry("localhost", 1099);

            Provider.Service service =
                    (Provider.Service) registry.lookup("Service");

            System.out.print("Nhap a: ");
            int a = sc.nextInt();

            System.out.print("Nhap b: ");
            int b = sc.nextInt();

            int result = service.add(a, b);

            System.out.println("Tong = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}