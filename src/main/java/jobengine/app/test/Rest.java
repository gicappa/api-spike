package jobengine.app.test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;

public class Rest {

    private final Dispatcher dispatcher;

    public Rest(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public MockHttpResponse process(MockHttpRequest request) {
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        return response;
    }
}
