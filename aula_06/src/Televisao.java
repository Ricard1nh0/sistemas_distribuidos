import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Televisao extends Remote {
    int adicionar(int a, int b) throws RemoteException;
    int subtrair(int a,int b) throws RemoteException;
}