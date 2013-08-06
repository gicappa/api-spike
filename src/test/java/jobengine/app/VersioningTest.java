package jobengine.app;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class VersioningTest {

    private Dispatcher dispatcher;

    @Before
    public void before() {
        dispatcher = MockDispatcherFactory.createDispatcher();
        POJOResourceFactory noDefaults = new POJOResourceFactory(ApiAdverts.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(noDefaults);;
    }

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        MockHttpRequest request = MockHttpRequest.get("/adverts");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
        assertThat(response.getContentAsString(), is(notNullValue()));
    }
}