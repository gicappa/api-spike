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

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.jboss.resteasy.mock.MockHttpRequest.get;
import static org.junit.Assert.assertThat;

// automagically injected by the MockResteasyTestExecutionListener
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(MockResteasyTestExecutionListener.class)
@ResteasyBean(beanName = "apiAdverts", beanClass = ApiAdverts.class)
public class VersioningTest {

    public static final String MEDIA_TYPE_ALPHA = "application/vnd.jobrapido.alpha+json";
    public static final MediaType MEDIA_TYPE_ALPHA_TYPE = MediaType.valueOf(MEDIA_TYPE_ALPHA);
    public static final String MEDIA_TYPE_V1 = "application/vnd.jobrapido.v1+json";
    public static final MediaType MEDIA_TYPE_V1_TYPE = MediaType.valueOf(MEDIA_TYPE_V1);
    public Rest rest;
    private MockHttpResponse response;

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        response = rest.process(get("/adverts"));
        expectContentType(response, MediaType.APPLICATION_JSON);
    }

    @Test
    public void when_media_type_is_json_accept_is_specified_return_json() throws Exception {
        response = rest.process(get("/adverts").accept(MediaType.APPLICATION_JSON));
        expectContentType(response, MediaType.APPLICATION_JSON);
    }

    @Test
    public void when_json_accept_is_specified_return_version_v1() throws Exception {
        response = rest.process(get("/adverts").accept(MEDIA_TYPE_V1));
        expectContentType(response, MEDIA_TYPE_V1);
    }

    @Test
    public void when_media_type_is_alpha_json_accept_is_specified_return_json() throws Exception {
        response = rest.process(get("/adverts").accept(MEDIA_TYPE_ALPHA));
        expectContentType(response, MEDIA_TYPE_ALPHA);
    }

    private void expectContentType(MockHttpResponse res, String contentType) {
        assertThat(res.getStatus(), is(HttpServletResponse.SC_OK));
        assertThat(res.getOutputHeaders().getFirst("Content-Type").toString(), is(contentType));
    }

    @Test
    public void when_no_media_type_is_specified_return_version_v1() throws Exception {
        response = rest.process(get("/adverts"));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("v1"));
    }

    @Test
    public void when_json_media_type_is_specified_return_version_v1() throws Exception {
        response = rest.process(get("/adverts").accept(MediaType.APPLICATION_JSON));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("v1"));
    }

    @Test
    public void when_media_type_is_v1_json_accept_is_specified_return_json() throws Exception {
        response = rest.process(get("/adverts").accept(MEDIA_TYPE_V1));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("v1"));
    }

    @Test
    public void when_media_type_is_alpha_json_accept_is_specified_return_alpha_version() throws Exception {
        response = rest.process(get("/adverts").accept(MEDIA_TYPE_ALPHA));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("alpha"));
    }

    @Test
    public void when_media_type_is_multiple_it_still_extract_the_version_found() throws Exception {
        response = rest.process(get("/adverts").accept(asList(MediaType.TEXT_PLAIN_TYPE, MEDIA_TYPE_ALPHA_TYPE)));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("alpha"));
    }

    @Test
    public void when_media_types_select_two_version_it_respond_406() throws Exception {
        response = rest.process(get("/adverts").accept(asList(MEDIA_TYPE_V1_TYPE, MEDIA_TYPE_ALPHA_TYPE)));
        assertThat(response.getStatus(), is(406));
    }

    @Test
    public void when_accept_has_many_types_on_the_same_row() throws Exception {
        response = rest.process(get("/adverts").header("Accept", "application/json,application/xhtml+xml,text/html"));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("v1"));


    }
}