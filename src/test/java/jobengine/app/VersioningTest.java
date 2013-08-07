package jobengine.app;

import jobengine.app.test.ResteasyMockRule;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class VersioningTest {

    public String beanName = "apiAdverts";
    public Class beanClass = ApiAdverts.class;
    @Rule
    @Autowired
    public ResteasyMockRule r;

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        MockHttpResponse res = r.get("/adverts/");
        assertThat(res.getStatus(), is(HttpServletResponse.SC_OK));
        assertThat(res.getContentAsString(), is(notNullValue()));
    }
}