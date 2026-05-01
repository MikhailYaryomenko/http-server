import java.io.*;
import java.net.*;

public class HttpServer {
    static final int PORT = 8000;

    static void main(String[] args) {
        while (true) {
            // The try-with-resources statement ensures that each resource is closed at the end of the statement.
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                Socket clientSocket = serverSocket.accept();

                // Enabling multiple clients with multithreading
                new Thread(new ClientHandler(clientSocket)).start();

            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
            }
        }
    }
}
