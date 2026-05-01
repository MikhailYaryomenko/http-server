package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    static final int PORT = 8000;

    static void main(String[] args) {
        while (true) {
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
