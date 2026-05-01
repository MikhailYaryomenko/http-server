import java.net.Socket;

public class ResponseController {

    private final Socket clientSocket;

    public ResponseController(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handleRequest() {
        RequestProcessor requestProcessor = new RequestProcessor(clientSocket);
        HttpRequestInfo httpRequestInfo = requestProcessor.getRequestInfo();
        System.out.println(httpRequestInfo);
    }
    // read request
    // read method
    // handle routing
    // request handler take client socket and generate path and method
    // router provide specific class depending on the route
    // methodFactory provides method depending on the method
}
