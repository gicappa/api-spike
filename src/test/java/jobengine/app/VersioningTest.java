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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.jboss.resteasy.mock.MockHttpRequest.get;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(MockResteasyTestExecutionListener.class)
public class VersioningTest {

    public Rest rest;

    @Test
    @ResteasyBean(beanName = "apiAdverts", beanClass = ApiAdverts.class)
    public void when_no_media_type_is_specified_return_json() throws Exception {
        MockHttpResponse res = rest.process(get("/adverts/"));
        assertThat(res.getStatus(), is(HttpServletResponse.SC_OK));
        assertThat(res.getContentAsString(), is(notNullValue()));
    }


}