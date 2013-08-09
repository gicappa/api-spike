package jobengine.app;

import jobengine.app.test.MockResteasyTestExecutionListener;
import jobengine.app.test.Rest;
import jobengine.app.test.ResteasyBean;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.jboss.resteasy.mock.MockHttpRequest.get;
import static org.jboss.resteasy.mock.MockHttpRequest.put;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(MockResteasyTestExecutionListener.class)
@ResteasyBean(beanName = "apiAdverts", beanClass = ApiAdverts.class)
public class AdvertResourceTest {

    public Rest rest;
    private MockHttpResponse response;

    @Test
    public void when_resource_not_existing_it_responds_404() throws Exception {
        response = rest.process(get("/not-existing-url"));
        assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

    @Test
    public void when_http_verb_is_not_implemented_responds_405() throws Exception {
        response = rest.process(put("/adverts"));
        assertThat(response.getStatus(), is(Response.Status.METHOD_NOT_ALLOWED.getStatusCode()));
    }

    @Test
    public void when_requesting_adverts_it_responds_200() throws Exception {
        response = rest.process(get("/adverts"));
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void when_requesting_adverts_with_params_it_responds_200() throws Exception {
        response = rest.process(get("/adverts?page=0&size=5"));
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void when_a_single_advert_is_requested_it_responds_200() throws Exception {
        response = rest.process(get("/adverts/4"));
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void when_advert_id_does_not_exist_it_responds_404() throws Exception {
        response = rest.process(get("/adverts/1000"));
        assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

}
