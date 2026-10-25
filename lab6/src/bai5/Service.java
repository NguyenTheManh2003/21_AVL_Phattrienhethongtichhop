
package bai5;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

    void sendMessage(String message)
            throws RemoteException;

    String getReply()
            throws RemoteException;
}