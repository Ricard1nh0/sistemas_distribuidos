import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorUDP {

    private static final int PORTA = 9876;
    private static final int TAMANHO_BUFFER = 1024;

    private static final Map<String, String> leiturasSensores = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORTA)) {
            System.out.println("Servidor UDP iniciado na porta " + PORTA);
            System.out.println("Aguardando leituras dos sensores...");

            byte[] buffer = new byte[TAMANHO_BUFFER];

            while (true) {
                DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
                
                socket.receive(pacoteRecebido); 


                String mensagem = new String(pacoteRecebido.getData(), 0, pacoteRecebido.getLength());

                processarMensagem(mensagem);
                exibirPainel();
            }

        } catch (SocketException e) {
            System.err.println("Erro ao criar o socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de I/O ao receber pacote: " + e.getMessage());
        }
    }

    private static void processarMensagem(String mensagem) {
        String[] partes = mensagem.split(":", 2);
        if (partes.length == 2) {
            String idSensor = partes[0].trim();
            String valor = partes[1].trim();
            leiturasSensores.put(idSensor, valor);
        } else {
            System.out.println("Mensagem mal formatada recebida: " + mensagem);
        }
    }

    private static void exibirPainel() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("--- PAINEL DE MONITORAMENTO DE SENSORES (UDP) ---");
        System.out.println("Última atualização: " + new java.util.Date());
        System.out.println("-------------------------------------------------");

        if (leiturasSensores.isEmpty()) {
            System.out.println("Nenhuma leitura recebida ainda...");
        } else {
            for (Map.Entry<String, String> entry : leiturasSensores.entrySet()) {
                System.out.printf("%-20s: %s\n", entry.getKey(), entry.getValue());
            }
        }
        System.out.println("-------------------------------------------------");
    }
}