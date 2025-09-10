import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            
            System.out.println("Conectado ao servidor. Digite suas mensagens. Digite 'sair' para encerrar.");
            
            String userInput;
            while (true) {
                System.out.print("> ");
                userInput = consoleReader.readLine(); 

                saida.println(userInput); 

                if ("sair".equalsIgnoreCase(userInput)) {
                    break;
                }
                
                String resposta = entrada.readLine();
                System.out.println("Servidor: " + resposta);
            }

        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: localhost");
        } catch (IOException e) {
            System.err.println("Não foi possível conectar ao servidor: " + e.getMessage());
        }
        System.out.println("Conexão encerrada.");
    }
}