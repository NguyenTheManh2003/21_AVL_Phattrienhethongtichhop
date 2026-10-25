package bai9;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuctionCallback extends Remote {

    void notifyBid(String message)
            throws RemoteException;
}