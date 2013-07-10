package jobengine.api;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class JobrapidoAPIConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HelloWorldResource.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<Object>();
        instances.add(new LoggingFilter());
        return instances;
    }
}