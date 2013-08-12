package jobengine.app.test;

import org.springframework.util.Assert;

public class ApiBeanDescriptor {
    private static final String NO_ANNOTATION_EXCEPTION = "a @ResteasyBean annotation MUST be specified at class or method level";
    private final ResteasyBean defaultResteasyBean;

    public ApiBeanDescriptor(ResteasyBean resteasyBean) {
        this.defaultResteasyBean = resteasyBean;
    }

    public String beanName(ResteasyBean resteasyBean) {
        if (resteasyBean != null)
            return verifiedBeanName(resteasyBean);

        if (defaultResteasyBean != null)
            return verifiedBeanName(defaultResteasyBean);

        throw new RuntimeException(NO_ANNOTATION_EXCEPTION);
    }

    public Class<?> beanClass(ResteasyBean resteasyBean) {
        if (resteasyBean != null)
            return verifiedBeanClass(resteasyBean);

        if (defaultResteasyBean != null)
            return verifiedBeanClass(defaultResteasyBean);

        throw new RuntimeException(NO_ANNOTATION_EXCEPTION);
    }

    private String verifiedBeanName(ResteasyBean resteasyBean) {
        Assert.notNull(resteasyBean.beanName(), "the specified beanName in @ResteasyBean can't be null");
        Assert.isTrue(!resteasyBean.beanName().isEmpty(), "the specified beanName in @ResteasyBean can't be null");
        return resteasyBean.beanName();
    }

    private Class<?> verifiedBeanClass(ResteasyBean resteasyBean) {
        Assert.notNull(resteasyBean.beanClass(), "the specified beanClass in @ResteasyBean can't be null");
        return resteasyBean.beanClass();
    }


}
