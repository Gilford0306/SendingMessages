import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Client: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("New message from server: " + message);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
