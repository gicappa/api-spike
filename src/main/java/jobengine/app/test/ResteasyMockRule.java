package jobengine.app.test;


import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.plugins.spring.SpringResourceFactory;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class ResteasyMockRule implements MethodRule {
    public static final String LIFECYCLE_ERROR_MESSAGE = "setBean(beanName, beanClass) MUST be called with the bean name and class that represent the JAX-RS resource. I.e. setBean(\"apiAdverts\", ApiAdverts.class";
    @Autowired
    public ApplicationContext context;
    private Dispatcher dispatcher;
    private String beanName;
    private Class<?> beanClass;
    private MockHttpRequest request;

    private Statement statement(final Statement base, Object target) {
        try {
            ResteasyMockRule.this.beanName = (String) fieldValue(target, "beanName");
            ResteasyMockRule.this.beanClass = (Class<?>) fieldValue(target, "beanClass");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                before();
                try {
                    base.evaluate();
                } finally {
                    after();
                }
            }
        };
    }

    private Object fieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return target.getClass().getField(fieldName).get(target);
    }

    private void after() {
    }

    protected void before() {
        dispatcher = MockDispatcherFactory.createDispatcher();
        SpringBeanProcessor processor = new SpringBeanProcessor(dispatcher, null, null);
        ((ConfigurableApplicationContext) context).addBeanFactoryPostProcessor(processor);
        SpringResourceFactory noDefaults = new SpringResourceFactory(beanName, context, beanClass);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
    }

    public MockHttpResponse get(String path) throws URISyntaxException {
        request = MockHttpRequest.get(path);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        return response;
    }

    public Dispatcher dispatcher() {
        return dispatcher;
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return statement(base, target);
    }
}
