package jobengine.app.request;

import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class JRRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    public JRRequestMappingHandlerMapping() {
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return super.getCustomMethodCondition(method);
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = handlerType.getAnnotation(ApiVersion.class);
        if (apiVersion == null || apiVersion.value() == null || apiVersion.value()[0] == null)
            return super.getCustomTypeCondition(handlerType);
        return new VersionRequestCondition(apiVersion.value()[0]);
    }
}
