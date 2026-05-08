package request;

import request.method.HttpMethodHandler;
import request.method.PostHandler;

import java.io.PrintWriter;
import java.util.Map;

public class RequestHandler {
    private final Map<String, HttpMethodHandler> handlers = Map.of(
            "POST", new PostHandler()
    );
    private HttpRequestInfo httpRequestInfo;
    private PrintWriter writer;
    private HttpStatusGenerator httpStatusGenerator;

    public RequestHandler(HttpRequestInfo httpRequestInfo, PrintWriter writer) {
        this.httpRequestInfo = httpRequestInfo;
        this.writer = writer;
        this.httpStatusGenerator = new HttpStatusGenerator(writer);
    }

    public void handleRequest() {
        System.out.println(httpRequestInfo);
        HttpMethodHandler handler = handlers.get(httpRequestInfo.method());
        if (handler == null) {
            httpStatusGenerator.generateStatusResponse("405");
            return;
        }
        handler.handle(httpRequestInfo.path(), writer);
    }
}
