package server;

import request.HttpRequestInfo;
import request.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Without buffering, every request to read a character might trigger a direct,
            // expensive interaction with the underlying hardware or operating system.
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String requestLine = in.readLine();
            String[] requestLineParts = requestLine.split(" ");

            HttpRequestInfo httpRequestInfo = new HttpRequestInfo(requestLineParts[0], requestLineParts[1]);
            RequestHandler requestHandler = new RequestHandler(httpRequestInfo, writer);
            requestHandler.handleRequest();

            while (requestLine != null && !requestLine.isEmpty()) {
                System.out.println(requestLine);
                requestLine = in.readLine();
            }

            System.out.println();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
