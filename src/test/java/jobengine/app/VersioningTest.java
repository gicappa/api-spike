package jobengine.app;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import jobengine.domain.Advert;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class VersioningTest extends JerseyTest {

    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder("jobengine.app")
                .servletClass(SpringServlet.class)
                .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
                .contextListenerClass(ContextLoaderListener.class)
                .contextPath("/")
                .servletPath("/api")
                .contextParam("contextConfigLocation", "classpath:applicationContext.xml")
                .build();
    }

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        WebResource webResource = resource();
        System.in.read();

        List<Advert> response = webResource.path("/adverts")
                .accept(MediaType.APPLICATION_JSON_TYPE).get(List.class);
        System.out.println(response);
        assertThat(response, is(notNullValue()));

    }
}