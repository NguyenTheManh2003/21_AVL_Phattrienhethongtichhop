package bai9;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server extends UnicastRemoteObject
        implements AuctionService {

    private final Map<String, Product> products;
    private final List<AuctionCallback> callbacks;

    public Server() throws RemoteException {
        super();

        products = new HashMap<>();
        callbacks = new ArrayList<>();
    }

    @Override
    public synchronized boolean addProduct(
            String name, double startPrice) {

        if (name == null || name.trim().isEmpty()
                || !Double.isFinite(startPrice)
                || startPrice <= 0
                || products.containsKey(name)) {
            return false;
        }

        Product product = new Product(name, startPrice);
        products.put(name, product);

        System.out.println("Da them san pham: " + name);

        notifyAllClients(
                "San pham moi: " + name
                        + " | Gia khoi diem: " + startPrice);

        return true;
    }

    @Override
    public synchronized List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public synchronized boolean placeBid(
            String productName,
            String bidder,
            double price) {

        Product product = products.get(productName);

        if (product == null
                || bidder == null
                || bidder.trim().isEmpty()
                || !Double.isFinite(price)
                || price <= product.getCurrentPrice()) {
            return false;
        }

        product.setCurrentPrice(price);
        product.setHighestBidder(bidder);

        String message =
                bidder + " da dat gia " + price
                        + " cho san pham " + productName;

        System.out.println(message);

        notifyAllClients(message);

        return true;
    }

    @Override
    public synchronized void registerCallback(
            AuctionCallback callback) {

        if (!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }

    @Override
    public synchronized void unregisterCallback(
            AuctionCallback callback) {

        callbacks.remove(callback);
    }

    private void notifyAllClients(String message) {
        List<AuctionCallback> disconnected =
                new ArrayList<>();

        for (AuctionCallback callback : callbacks) {
            try {
                callback.notifyBid(message);
            } catch (RemoteException e) {
                disconnected.add(callback);
            }
        }

        callbacks.removeAll(disconnected);
    }

    public static void main(String[] args) {
        try {
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            Server server = new Server();

            registry.rebind("AuctionService", server);

            System.out.println(
                    "Auction Server dang chay...");

            System.out.println(
                    "Dang cho Seller va Bidder ket noi...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}