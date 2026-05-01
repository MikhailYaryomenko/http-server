package request.method;

import java.io.PrintWriter;

public class Sender {
    PrintWriter writer;

    public Sender(PrintWriter writer) {
        this.writer = writer;
    }

    public void send(String message, String contentType) {
        // While you can write raw bytes directly to the OutputStream,
        // it is easier to wrap it in a higher-level writer
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + message.length());
        writer.println();
        writer.println(message);
    }
}
