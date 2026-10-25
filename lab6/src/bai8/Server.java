package bai8;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server extends UnicastRemoteObject
        implements Service {

    private final HashMap<String, Integer> flights;
    private final HashMap<String, Integer> capacity;

    public Server() throws RemoteException {
        super();

        flights = new HashMap<>();
        capacity = new HashMap<>();

        flights.put("VN101", 50);
        flights.put("VN202", 30);
        flights.put("VN303", 20);

        capacity.putAll(flights);
    }

    @Override
    public synchronized int availableSeats(String flightNumber) {
        return flights.getOrDefault(flightNumber, -1);
    }

    @Override
    public synchronized boolean bookTicket(
            String flightNumber, int seats) {

        if (!flights.containsKey(flightNumber) || seats <= 0) {
            return false;
        }

        int available = flights.get(flightNumber);

        if (available >= seats) {
            flights.put(flightNumber, available - seats);

            System.out.println(
                    "Da dat " + seats
                            + " ghe chuyen " + flightNumber);

            return true;
        }

        return false;
    }

    @Override
    public synchronized boolean cancelBooking(
            String flightNumber, int seats) {

        if (!flights.containsKey(flightNumber) || seats <= 0) {
            return false;
        }

        int available = flights.get(flightNumber);
        int maxSeats = capacity.get(flightNumber);

        if (seats <= maxSeats - available) {
            flights.put(flightNumber, available + seats);

            System.out.println(
                    "Da huy " + seats
                            + " ghe chuyen " + flightNumber);

            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind("Service", new Server());

            System.out.println("Flight Server dang chay...");
            System.out.println("VN101: 50 ghe");
            System.out.println("VN202: 30 ghe");
            System.out.println("VN303: 20 ghe");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}