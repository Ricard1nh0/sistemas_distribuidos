import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 12345;
        System.out.println("Servidor Iniciado. Aguardando clientes na porta " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // Loop infinito para sempre aceitar novas conexões
            while (true) {
                // Fica bloqueado aqui até um novo cliente se conectar
                Socket clientSocket = serverSocket.accept(); 
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Cria um novo "garçom" (Handler) para cuidar deste cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                
                // Inicia o "garçom" em uma nova thread para não bloquear o servidor
                new Thread(clientHandler).start();
            }
        }
    }
}