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

    private ApiBeanDescriptor beanDescriptor;

    @Override
    public void beforeTestClass(TestContext testCtx) throws Exception {
        super.beforeTestClass(testCtx);
        beanDescriptor = new ApiBeanDescriptor(classAnnotation(testCtx));
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        super.prepareTestInstance(testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testCtx) throws Exception {
        super.beforeTestClass(testCtx);

        ApplicationContext ctx = testCtx.getApplicationContext();
        setupResteasySpringIntegration(ctx, getBeanName(testCtx), getBeanClass(testCtx));
        injectDispatcherInto(testCtx.getTestInstance());
    }

    private Class<?> getBeanClass(TestContext testCtx) {
        return beanDescriptor.beanClass(methodAnnotation(testCtx));
    }

    private String getBeanName(TestContext testCtx) {
        return beanDescriptor.beanName(methodAnnotation(testCtx));
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

    private ResteasyBean methodAnnotation(TestContext testContext) {
        return testContext.getTestMethod().getAnnotation(ResteasyBean.class);
    }
    private ResteasyBean classAnnotation(TestContext testContext) {
        return testContext.getTestClass().getAnnotation(ResteasyBean.class);
    }

    private void setupResteasySpringIntegration(ApplicationContext context, String beanName, Class<?> beanClass) {
        dispatcher = MockDispatcherFactory.createDispatcher();
        SpringBeanProcessor processor = new SpringBeanProcessor(dispatcher, null, null);
        ((ConfigurableApplicationContext) context).addBeanFactoryPostProcessor(processor);
        SpringResourceFactory noDefaults = new SpringResourceFactory(beanName, context, beanClass);
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