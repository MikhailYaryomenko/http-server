package request.method;

import java.io.PrintWriter;

public class GetHandler implements HttpMethodHandler {

    // testing git
    @Override
    public void handle(String path, PrintWriter writer) {
        Sender sender = new Sender(writer);
        sender.send("Hi, from GetHandler", "text/plain");
    }
}
