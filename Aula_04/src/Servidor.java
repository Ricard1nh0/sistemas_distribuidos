import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(12345)) {
            System.out.println("Servidor aguardando conexões na porta 12345...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)) {

                    String mensagem;
                    while ((mensagem = entrada.readLine()) != null) {
                        System.out.println("Cliente diz: " + mensagem);

                        if ("sair".equalsIgnoreCase(mensagem)) {
                            saida.println("Ok, encerrando conexão. Até logo!");
                            break;
                        }

                        saida.println("Servidor recebeu: '" + mensagem + "'");
                    }
                } catch (IOException e) {
                    System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
                } finally {
                    System.out.println("Conexão com o cliente " + socket.getInetAddress().getHostAddress() + " encerrada.");
                    socket.close(); 
                }
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}