package request.method;

import request.HttpStatusGenerator;

import java.io.PrintWriter;
import java.util.Objects;

public class GetHandler implements HttpMethodHandler {


    @Override
    public void handle(String path, PrintWriter writer) {
        if (!Objects.equals(path, "/")) {
            HttpStatusGenerator httpStatusGenerator = new HttpStatusGenerator(writer);
            httpStatusGenerator.generateStatusResponse("404");
            return;
        }
        Sender sender = new Sender(writer);
        sender.send("Hi, from GetHandler", "text/plain");
    }
}
