package bai6;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject
        implements Service {

    private double balance = 1000000;

    public Server() throws RemoteException {
        super();
    }

    @Override
    public synchronized double getBalance() {
        return balance;
    }

    @Override
    public synchronized void deposit(double amount)
            throws RemoteException {

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new RemoteException(
                    "So tien nap phai lon hon 0");
        }

        balance += amount;

        System.out.println("Da nap: " + amount);
        System.out.println("So du moi: " + balance);
    }

    @Override
    public synchronized boolean withdraw(double amount) {
        if (!Double.isFinite(amount)
                || amount <= 0
                || amount > balance) {
            return false;
        }

        balance -= amount;

        System.out.println("Da rut: " + amount);
        System.out.println("So du moi: " + balance);

        return true;
    }

    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind("Service", new Server());

            System.out.println("Bank Server dang chay...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}