import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Controle extends Remote{
    void ligarDesligar() throws RemoteException;
}
