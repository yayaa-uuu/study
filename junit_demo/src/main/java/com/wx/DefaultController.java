package com.wx;

import java.util.HashMap;
import java.util.Map;

public class DefaultController implements Controller {
    private Map requestHandlers = new HashMap<>();

    protected RequestHandler getHandler(Request request) {
        if (!this.requestHandlers.containsKey(request.getName())) {
            String message = "Cannot find handler for request name " +
                    "{" + request.getName() + "}";
            throw new RuntimeException(message);
        }
        return (RequestHandler) this.requestHandlers.get(request.getName());
    }

    @Override
    public Response processRequest(Request request) {
        Response respond;
        try {
            respond = getHandler(request).process(request);
        } catch (Exception exception) {
            respond = new ErrorResponse(request, exception);
        }
        return respond;
    }

    @Override
    public void addHandler(Request request, RequestHandler requestHandler) {
        if (this.requestHandlers.containsKey(request.getName())) {
            throw new RuntimeException("A request handler has " +
                    "already been registered for request name." +
                    "{" + request.getName() + "}");
        } else {
            this.requestHandlers.put(request.getName(), requestHandler);
        }
    }
}
