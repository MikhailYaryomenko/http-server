package request;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpStatusGenerator {

    private final Map<String, String> httpCodes = Map.of(
            "404", "404 Not Found",
            "405", "405 Method Not Allowed"
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

        writer.print("HTTP/1.1 " + httpStatusMessage + "\r\n");
        writer.print("Content-Type: text/plain\r\n");
        writer.print("Allow: GET, POST\r\n");
        // HTTP actually expects amount of bytes. It's especially important,
        // because in many languages single character can be 2 bytes
        writer.print("Content-Length: " + httpStatusMessage.getBytes(StandardCharsets.UTF_8).length + "\r\n");
        writer.print("\r\n");
        writer.print(httpStatusMessage);
        writer.flush();
    }
}
