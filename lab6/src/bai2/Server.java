package bai2;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.Provider;

public class Server extends UnicastRemoteObject
        implements Provider.Service {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind("Service", new Server());

            System.out.println("Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}