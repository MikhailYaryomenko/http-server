package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static int port;

    static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Port number is need to be specified as command-line argument");
            return;
        }
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("The first argument must be a valid port number");
        }
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                // Creating new thread on each iteration so multiple clients can connect
                new Thread(new ClientHandler(clientSocket)).start();
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
                break;
            }
        }
    }
}
