package bai3;
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

            System.out.print("Nhap n: ");
            int n = sc.nextInt();

            if (service.isPrime(n)) {
                System.out.println(n + " la so nguyen to");
            } else {
                System.out.println(n + " khong phai so nguyen to");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}