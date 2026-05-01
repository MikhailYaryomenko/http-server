import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ResponseSender {

    private Socket clientSocket;

    public ResponseSender(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void respond(String message, String contentType) throws IOException {

        // While you can write raw bytes directly to the OutputStream,
        // it is easier to wrap it in a higher-level writer
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + message.length());
        writer.println("");
        writer.println(message);
    }
}
