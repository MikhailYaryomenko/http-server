import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

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

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
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
