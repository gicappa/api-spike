package jobengine.app.test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.plugins.spring.SpringResourceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.lang.reflect.Field;

public class MockResteasyTestExecutionListener extends DependencyInjectionTestExecutionListener {

    private Dispatcher dispatcher;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        super.beforeTestClass(testContext);
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        super.prepareTestInstance(testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testCtx) throws Exception {
        super.beforeTestClass(testCtx);
        ApplicationContext ctx = testCtx.getApplicationContext();
        ResteasyBean annotation = fetchAnnotation(testCtx);
        Object testInstance = testCtx.getTestInstance();

        setupResteasySpringIntegration(ctx, annotation);
        injectDispatcherInto(testInstance);
    }

    private void injectDispatcherInto(Object bean) throws IllegalAccessException {
        for (Field field : bean.getClass().getFields()) {
            if (isNotDispatcher(field)) continue;

            injectField(bean, field);
        }
    }

    private void injectField(Object bean, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(bean, new Rest(dispatcher));
    }

    private boolean isNotDispatcher(Field field) {
        return !field.getType().equals(Rest.class);
    }

    private ResteasyBean fetchAnnotation(TestContext testContext) {
        return testContext.getTestMethod().getAnnotation(ResteasyBean.class);
    }

    private void setupResteasySpringIntegration(ApplicationContext context, ResteasyBean annotation) {
        dispatcher = MockDispatcherFactory.createDispatcher();
        SpringBeanProcessor processor = new SpringBeanProcessor(dispatcher, null, null);
        ((ConfigurableApplicationContext) context).addBeanFactoryPostProcessor(processor);
        SpringResourceFactory noDefaults = new SpringResourceFactory(annotation.beanName(), context, annotation.beanClass());
        dispatcher.getRegistry().addResourceFactory(noDefaults);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        super.afterTestMethod(testContext);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        super.afterTestClass(testContext);
    }
}