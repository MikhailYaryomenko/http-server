import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        ResponseController responseController = new ResponseController(clientSocket);
        responseController.handleRequest();
//            BufferedReader in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null && !line.isEmpty()) {
//                System.out.println(line);
//            }
//
//            clientResponder.respond("Hello World", "text/plain");

    }
}
