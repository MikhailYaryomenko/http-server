package request;

import java.io.PrintWriter;
import java.util.Map;

public class HttpStatusGenerator {

    private final Map<String, String> httpCodes = Map.of(
            "404", "404 Not Found",
            "405", "Method Not Allowed"
    );
    private PrintWriter writer;

    public HttpStatusGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generateStatusResponse(String statusCode) {
        String httpStatusMessage = httpCodes.get(statusCode);
        if (httpStatusMessage == null) {
            System.err.println("You provided unsupported status code");
            throw new RuntimeException();
        }
        writer.println("HTTP/1.1 " + httpStatusMessage);
        writer.println("Content-Type: plain/text");
        writer.println("Allow: GET, HEAD");
        writer.println("Content-Length: " + httpStatusMessage.length());
        writer.println();
        writer.println(httpStatusMessage);
    }
}
