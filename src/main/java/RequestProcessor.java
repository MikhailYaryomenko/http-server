import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestProcessor {
    private Socket clientSocket;

    public RequestProcessor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public HttpRequestInfo getRequestInfo() {
        try {
            // Without buffering, every request to read a character might trigger a direct,
            // expensive interaction with the underlying hardware or operating system.
            BufferedReader in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String requestLine = in.readLine();
            String[] requestLineParts = requestLine.split(" ");
            in.close();
            return new HttpRequestInfo(requestLineParts[0], requestLineParts[1]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
