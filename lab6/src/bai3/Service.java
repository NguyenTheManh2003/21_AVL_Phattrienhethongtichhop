package bai3;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    boolean isPrime(int n) throws RemoteException;
}