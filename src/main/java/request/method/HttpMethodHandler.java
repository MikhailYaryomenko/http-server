package request.method;

import java.io.PrintWriter;

public interface HttpMethodHandler {
    void handle(String path, PrintWriter writer);
}
