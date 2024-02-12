import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BroadcastServer {
    private static final int PORT = 5555;
    private static final List<PrintWriter> clientOutputStreams = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server start - " + PORT);
            Thread broadcastThread = new Thread(new BroadcastTask());
            broadcastThread.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("\n New client: " + clientSocket);
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clientOutputStreams.add(writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class BroadcastTask implements Runnable {
        @Override
        public void run() {
            Scanner in = new Scanner(System.in);

            while (true) {
                System.out.print("Input mess: ");
                String message = in.nextLine();
                System.out.println("Message \""+ message + "\" is send.");
                for (PrintWriter writer : clientOutputStreams) {
                    writer.println(message);
                    writer.flush();
                }
            }
        }
    }
}
