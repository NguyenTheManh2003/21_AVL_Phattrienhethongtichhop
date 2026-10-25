package bai9;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionService extends Remote {

    boolean addProduct(String name, double startPrice)
            throws RemoteException;

    List<Product> getProducts()
            throws RemoteException;

    boolean placeBid(
            String productName,
            String bidder,
            double price
    ) throws RemoteException;

    void registerCallback(AuctionCallback callback)
            throws RemoteException;

    void unregisterCallback(AuctionCallback callback)
            throws RemoteException;
}