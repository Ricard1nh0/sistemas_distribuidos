import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Conecta no registry local (use IP/hostname do servidor se for remoto)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Televisao calc = (Televisao) registry.lookup("CalculadoraService");

            System.out.println("10 + 5 = " + calc.adicionar(10, 5));
            System.out.println("10 - 5 = " + calc.subtrair(10, 5));
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e);
            e.printStackTrace();
        }
    }
}