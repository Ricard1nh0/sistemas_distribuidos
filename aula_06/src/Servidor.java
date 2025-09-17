import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements Televisao {
    public Servidor() throws RemoteException{
        super();
    }
    
    @Override
    public int adicionar(int a, int b) throws RemoteException {
        System.out.println("Adicionando " + a + " e " + b);
        return a + b;
    }

    @Override
    public int subtrair(int a, int b) throws RemoteException {
        System.out.println("Subtraindo " + a + " de " + b);
        return a - b;
    }
    
    public static void main(String[] args){
        try {
            // Sobe o registry na própria JVM (porta padrão 1099)
            // Se já existir, esse createRegistry lança exceção — tratamos abaixo.
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("RMI Registry iniciado na porta 1099.");
            } catch (RemoteException e) {
                System.out.println("RMI Registry já estava em execução.");
            }

            Servidor obj = new Servidor();
            // Faz o bind via API do Registry (mais moderno que Naming.rebind)
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("CalculadoraService", obj);

            System.out.println("Servidor da Calculadora pronto.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e);
            e.printStackTrace();
        }
    }
    
}