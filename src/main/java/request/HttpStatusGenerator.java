package request;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

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
        if (Objects.equals(statusCode, "405")) {
            writer.print("Allow: GET, POST\r\n");
        }
        // HTTP Content-Length header expects the size of the message body
        // expressed in bytes. Be aware that some characters have size of 2 bytes
        // that results in errors with length of the String based on characters
        writer.print("Content-Length: " + httpStatusMessage.getBytes(StandardCharsets.UTF_8).length + "\r\n");
        writer.print("\r\n");
        writer.print(httpStatusMessage);
        writer.flush();
    }
}
