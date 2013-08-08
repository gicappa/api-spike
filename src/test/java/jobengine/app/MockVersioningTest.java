package jobengine.app;

import jobengine.app.test.MockResteasyTestExecutionListener;
import jobengine.app.test.Rest;
import jobengine.app.test.ResteasyBean;
import jobengine.domain.Adverts;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.jboss.resteasy.mock.MockHttpRequest.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml", "classpath:applicationContext.xml"})
@TestExecutionListeners(MockResteasyTestExecutionListener.class)
@ResteasyBean(beanName = "apiAdverts", beanClass = ApiAdverts.class)
public class MockVersioningTest {

    public Rest rest;

    @Autowired
    private Mockery context;

    @Autowired
    private Adverts adverts;

    @Test
    public void when_media_type_is_alpha_json_search_alpha_has_been_called() throws Exception {
        context.checking(new Expectations() {{
            oneOf(adverts).search_alpha(with(any(String.class)), with(any(String.class)));
            will(returnValue(newArrayList()));
        }});
        rest.process(get("/adverts").accept("application/vnd.jobrapido.alpha+json"));
        context.assertIsSatisfied();

    }
    @Test
    public void when_media_type_is_v1_search_has_been_called() throws Exception {
        context.checking(new Expectations() {{
            oneOf(adverts).search(with(any(String.class)), with(any(String.class)));
            will(returnValue(newArrayList()));
        }});
        rest.process(get("/adverts").accept("application/vnd.jobrapido.v1+json"));
        context.assertIsSatisfied();
    }

    @Test
    public void when_no_media_type_is_provided_search_has_been_called() throws Exception {
        context.checking(new Expectations() {{
            oneOf(adverts).search(with(any(String.class)), with(any(String.class)));
            will(returnValue(newArrayList()));
        }});
        rest.process(get("/adverts"));
        context.assertIsSatisfied();

    }
}