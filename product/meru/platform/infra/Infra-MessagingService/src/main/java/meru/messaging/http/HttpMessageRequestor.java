package meru.messaging.http;

import meru.messaging.MessageRequestor;

public class HttpMessageRequestor extends HttpHandler implements MessageRequestor {

    public HttpMessageRequestor() {

    }

    public Object request(String subject) {

        return get(subject);
    }
}
