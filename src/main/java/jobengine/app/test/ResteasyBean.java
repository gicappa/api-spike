package jobengine.app.test;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResteasyBean {
    String beanName();

    Class<?> beanClass();
}