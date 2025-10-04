import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

// Runnable permite que esta classe seja executada por uma Thread
public class ClientHandler implements Runnable {
    private Socket clientSocket;

    // O construtor recebe o socket do cliente específico
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String clientMessage;
            // ler mensagens
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Mensagem do cliente: " + clientSocket.getInetAddress().getHostAddress() + ": " + clientMessage);

                if ("sair".equalsIgnoreCase(clientMessage)) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no handler do cliente: " + e.getMessage());
        } finally {
            System.out.println("Cliente desconectado: " + clientSocket.getInetAddress().getHostAddress());
            // Aqui você fecharia o socket, etc.
        }
    }
}