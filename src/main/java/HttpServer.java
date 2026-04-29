import java.io.*;
import java.net.*;

public class HttpServer {
    static final int PORT = 8000;

    private static void sendHtml(String html, Socket clientSocket) throws IOException {
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html");
        writer.println("Content-Length: " + html.length());
        writer.println("");
        writer.println(html);
    }

    private static void sendMessage(String message, Socket clientSocket) throws IOException {

        // While you can write raw bytes directly to the OutputStream,
        // it is easier to wrap it in a higher-level writer
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/plain");
        writer.println("Content-Length: " + message.length());
        writer.println("");
        writer.println(message);
    }

    public static void main(String[] args) {
        while (true) {
            // The try-with-resources statement ensures that each resource is closed at the end of the statement.
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                Socket clientSocket = serverSocket.accept();

                // Without buffering, every request to read a character might trigger a direct,
                // expensive interaction with the underlying hardware or operating system.
                BufferedReader in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line;
                while ((line = in.readLine()) != null && !line.isEmpty()) {
                    System.out.println(line);
                }

                sendMessage("Hello World", clientSocket);

            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
            }
        }
    }
}
