package request.method;

import java.io.PrintWriter;

public class GetHandler implements HttpMethodHandler {

    /*
     Examples of HTTP response encoding for serving static files
     TODO: need to implement serving static files
     HTTP/1.1 200
     content-encoding: br
     content-type: text/javascript; charset=utf-8
     vary: Accept-Encoding
     date: Fri, 21 Jun 2024 14:02:25 GMT
     content-length: 2978
     HTTP/3 200
     server: nginx
     date: Wed, 24 Jul 2024 16:53:02 GMT
     content-type: text/css
     vary: Accept-Encoding
     content-encoding: br
    */

    @Override
    public void handle(String path, PrintWriter writer) {
        Sender sender = new Sender(writer);
        sender.send("Hi, from GetHandler", "text/plain");
    }
}
