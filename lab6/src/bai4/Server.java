package bai4;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server extends UnicastRemoteObject
        implements Service {

    private final HashMap<String, String> contacts;

    public Server() throws RemoteException {
        super();
        contacts = new HashMap<>();
    }

    @Override
    public synchronized void addContact(
            String name, String phone) {

        contacts.put(name, phone);
        System.out.println("Da them: " + name);
    }

    @Override
    public synchronized String findContact(String name) {
        return contacts.getOrDefault(
                name, "Khong tim thay lien he");
    }

    @Override
    public synchronized boolean deleteContact(String name) {
        return contacts.remove(name) != null;
    }

    @Override
    public synchronized boolean updateContact(
            String name, String phone) {

        if (contacts.containsKey(name)) {
            contacts.put(name, phone);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind("Service", new Server());

            System.out.println("Server danh ba dang chay...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}