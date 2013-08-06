package jobengine.app;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static junit.framework.Assert.assertEquals;

public class VersioningTest  {

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        POJOResourceFactory noDefaults = new POJOResourceFactory(ApiAdverts.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        {
            MockHttpRequest request = MockHttpRequest.get("/adverts");
            MockHttpResponse response = new MockHttpResponse();

            dispatcher.invoke(request, response);

            assertEquals(HttpServletResponse.SC_OK, response.getStatus());
//            assertEquals("basic", response.getContentAsString());
        }
    }
}