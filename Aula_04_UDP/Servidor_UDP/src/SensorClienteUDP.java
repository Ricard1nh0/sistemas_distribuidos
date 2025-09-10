import java.io.IOException;
import java.net.*;
import java.util.Random;

public class SensorClienteUDP {

    private static final String ENDERECO_SERVIDOR = "localhost";
    private static final int PORTA_SERVIDOR = 9876;
    private static final int INTERVALO_ENVIO_MS = 2000;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java SensorClienteUDP <ID_do_Sensor>");
            System.out.println("Exemplo: java SensorClienteUDP Sensor-Temperatura");
            return;
        }
        String idSensor = args[0];

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress enderecoServidor = InetAddress.getByName(ENDERECO_SERVIDOR);
            Random random = new Random();

            System.out.println("Sensor '" + idSensor + "' iniciado. Enviando dados para " + ENDERECO_SERVIDOR + ":" + PORTA_SERVIDOR);

            while (true) {
                double leitura = 20 + (10 * random.nextDouble()); 
                String unidade = getUnidadePorId(idSensor);
                String mensagem = String.format("%s: %.1f%s", idSensor, leitura, unidade);

                byte[] buffer = mensagem.getBytes();

                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, enderecoServidor, PORTA_SERVIDOR);

                socket.send(pacote);
                System.out.println("Enviando: " + mensagem);

                Thread.sleep(INTERVALO_ENVIO_MS);
            }

        } catch (SocketException | UnknownHostException e) {
            System.err.println("Erro de socket ou host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de I/O ao enviar pacote: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Sensor interrompido.");
            Thread.currentThread().interrupt();
        }
    }
    
    private static String getUnidadePorId(String id) {
        if (id.toLowerCase().contains("temperatura")) return "°C";
        if (id.toLowerCase().contains("umidade")) return "%RH";
        if (id.toLowerCase().contains("pressao")) return " hPa";
        return "";
    }
}