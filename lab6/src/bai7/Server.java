package bai7;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject
        implements Service {

    public Server() throws RemoteException {
        super();
    }

    private void checkPositive(double value) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(
                    "Kich thuoc phai lon hon 0");
        }
    }

    @Override
    public double rectangleArea(
            double width, double height) {

        checkPositive(width);
        checkPositive(height);

        return width * height;
    }

    @Override
    public double circleArea(double radius) {
        checkPositive(radius);

        return Math.PI * radius * radius;
    }

    @Override
    public double triangleArea(
            double base, double height) {

        checkPositive(base);
        checkPositive(height);

        return base * height / 2;
    }

    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind("Service", new Server());

            System.out.println("Geometry Server dang chay...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}