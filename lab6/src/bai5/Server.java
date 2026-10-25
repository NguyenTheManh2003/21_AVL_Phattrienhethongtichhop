package bai5;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server extends UnicastRemoteObject
        implements Service {

    private String lastMessage = "";
    private String reply = "";

    public Server() throws RemoteException {
        super();
    }

    @Override
    public synchronized void sendMessage(String message) {
        lastMessage = message;
        System.out.println("\nClient: " + message);
        notifyAll();
    }

    @Override
    public synchronized String getReply() {
        String result = reply;
        reply = "";
        return result;
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();

            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind("Service", server);

            System.out.println("Chat Server dang chay...");
            System.out.println("Cho tin nhan tu Client...");

            Scanner sc = new Scanner(System.in);

            while (true) {
                synchronized (server) {
                    while (server.lastMessage.isEmpty()) {
                        server.wait();
                    }

                    if (server.lastMessage.equalsIgnoreCase("/exit")) {
                        System.out.println("Client da thoat.");
                        server.lastMessage = "";
                        continue;
                    }

                    server.lastMessage = "";
                }

                System.out.print("Server tra loi: ");
                String response = sc.nextLine();

                synchronized (server) {
                    server.reply = response;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}