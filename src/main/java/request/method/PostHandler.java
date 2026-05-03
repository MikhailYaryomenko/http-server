package request.method;

import java.io.PrintWriter;

public class PostHandler implements HttpMethodHandler {

    @Override
    public void handle(String path, PrintWriter writer) {
        Sender sender = new Sender(writer);
        sender.send("Hi, from PostHandler", "text/plain");
    }
}
