package request;

import java.io.IOException;
import java.io.PrintWriter;

public class RequestHandler {
    private HttpRequestInfo httpRequestInfo;
    private PrintWriter writer;

    public RequestHandler(HttpRequestInfo httpRequestInfo, PrintWriter writer) {
        this.httpRequestInfo = httpRequestInfo;
        this.writer = writer;
    }

    public void handleRequest() {
        System.out.println(httpRequestInfo);
        try {
            if (httpRequestInfo.method().equals("GET")) {
                if (httpRequestInfo.path().equals("/")) {
                    respond("Hello World", "text/plain");
                } else {
                    respond("404: Page not found", "text/plain");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void respond(String message, String contentType) throws IOException {
        // While you can write raw bytes directly to the OutputStream,
        // it is easier to wrap it in a higher-level writer
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + message.length());
        writer.println("");
        writer.println(message);
    }
}
