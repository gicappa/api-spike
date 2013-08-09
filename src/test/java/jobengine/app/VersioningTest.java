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

import static org.hamcrest.CoreMatchers.is;
import static org.jboss.resteasy.mock.MockHttpRequest.get;
import static org.junit.Assert.assertThat;

// automagically injected by the MockResteasyTestExecutionListener
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(MockResteasyTestExecutionListener.class)
@ResteasyBean(beanName = "apiAdverts", beanClass = ApiAdverts.class)
public class VersioningTest {

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
        response = rest.process(get("/adverts").accept("application/vnd.jobrapido.v1+json"));
        expectContentType(response, "application/vnd.jobrapido.v1+json");
    }

    @Test
    public void when_media_type_is_alpha_json_accept_is_specified_return_json() throws Exception {
        response = rest.process(get("/adverts").accept("application/vnd.jobrapido.alpha+json"));
        expectContentType(response, "application/vnd.jobrapido.alpha+json");
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
    public void when_media_type_is_alpha_json_accept_is_specified_return_alpha_version() throws Exception {
        response = rest.process(get("/adverts").accept("application/vnd.jobrapido.alpha+json"));
        assertThat(response.getOutputHeaders().getFirst("X-Jobrapido-Media-Type").toString(), is("alpha"));
    }
//
//    @Test
//    public void when_media_type_is_v1_json_accept_is_specified_return_json() throws Exception {
//        rest.perform(get("/adverts").accept(MEDIA_TYPE_V1)).andExpect(content().contentType("application/json;charset=utf-8"));
//    }
//
//    @Test
//    public void when_media_type_is_v1_json_accept_is_specified_return_v1_version() throws Exception {
//        rest.perform(get("/adverts").accept(MEDIA_TYPE_V1)).andExpect(header().string("X-Jobrapido-Media-Type", "v1"));
//    }
//
//    @Test
//    public void when_media_type_is_multiple_it_still_extract_the_version_found() throws Exception {
//        rest.perform(get("/adverts").accept(MediaType.TEXT_PLAIN, MEDIA_TYPE_ALPHA)).andExpect(header().string("X-Jobrapido-Media-Type", "alpha"));
//    }
//    @Test
//    public void when_media_types_select_two_version_it_respond_406() throws Exception {
//        rest.perform(get("/adverts").accept(MEDIA_TYPE_V1, MEDIA_TYPE_ALPHA)).andExpect(status().isNotAcceptable());
//    }

}