package bai3;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject
        implements Service {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
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