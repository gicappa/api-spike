package jobengine.app;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.plugins.spring.SpringResourceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class VersioningTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Dispatcher dispatcher;

    @Before
    public void before() {
        dispatcher = MockDispatcherFactory.createDispatcher();

        SpringBeanProcessor processor = new SpringBeanProcessor(dispatcher,
                null, null);
        ((ConfigurableApplicationContext) applicationContext)
                .addBeanFactoryPostProcessor(processor);

        SpringResourceFactory noDefaults = new SpringResourceFactory(
                "apiAdverts", applicationContext, ApiAdverts.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
    }

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        MockHttpRequest request = MockHttpRequest.get("/adverts");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
        assertThat(response.getContentAsString(), is(notNullValue()));
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}