package request;

import request.method.GetHandler;
import request.method.HttpMethodHandler;
import request.method.PostHandler;

import java.io.PrintWriter;
import java.util.Map;

public class RequestHandler {
    private final Map<String, HttpMethodHandler> handlers = Map.of(
            "GET", new GetHandler(),
            "POST", new PostHandler()
    );
    private HttpRequestInfo httpRequestInfo;
    private PrintWriter writer;

    public RequestHandler(HttpRequestInfo httpRequestInfo, PrintWriter writer) {
        this.httpRequestInfo = httpRequestInfo;
        this.writer = writer;
    }

    public void handleRequest() {
        System.out.println(httpRequestInfo);
        HttpMethodHandler handler = handlers.get(httpRequestInfo.method());
        if (handler == null) {
            System.err.println(httpRequestInfo.method() + " method isn't supported");
            return;
        }
        handler.handle(httpRequestInfo.path(), writer);
    }
}
