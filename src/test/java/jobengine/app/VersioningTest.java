package jobengine.app;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import jobengine.domain.Advert;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class VersioningTest extends JerseyTest {

    @Override
    protected AppDescriptor configure() {
        WebAppDescriptor build = new WebAppDescriptor.Builder("jobengine.app")
                .servletClass(SpringServlet.class)
                .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
                .contextListenerClass(ContextLoaderListener.class)
                .requestListenerClass(RequestContextListener.class)
                .contextPath("/")
                .servletPath("/")
                .contextParam("contextConfigLocation", "classpath:applicationContext.xml")
                .build();
        build.getClientConfig().getProperties().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        System.out.println(build.getClientConfig().getFeatures());
        return build;
    }

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(getBaseURI()).path("/adverts/");
        GenericType< List <Advert>> list = new GenericType<List<Advert>>() {};
        List<Advert> response = webResource.accept("application/json").get(list);
        System.out.println(response);
        assertThat(response, is(notNullValue()));

    }
}