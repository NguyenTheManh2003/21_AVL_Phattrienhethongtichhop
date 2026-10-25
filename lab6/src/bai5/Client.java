package bai5;
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

            System.out.println("Da ket noi Chat Server!");
            System.out.println("Nhap /exit de thoat.");

            while (true) {
                System.out.print("\nClient: ");
                String message = sc.nextLine();

                service.sendMessage(message);

                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }

                System.out.println("Dang cho Server tra loi...");

                String reply = "";

                while (reply.isEmpty()) {
                    reply = service.getReply();

                    if (reply.isEmpty()) {
                        Thread.sleep(300);
                    }
                }

                System.out.println("Server: " + reply);
            }

            System.out.println("Da ngat ket noi.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}