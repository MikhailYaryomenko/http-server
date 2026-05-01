import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private ClientResponder clientResponder;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.clientResponder = new ClientResponder(clientSocket);
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

            clientResponder.respond("Hello World", "text/plain");

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
