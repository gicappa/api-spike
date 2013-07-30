package jobengine.application;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import jobengine.application.utils.WebServer;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(HttpJUnitRunner.class)
public class RestfulAdverts {

    @ClassRule
    public static WebServer g = new WebServer();
    @Rule
    public Destination d = new Destination(this, "http://localhost:8080/api/");
    @Context
    private Response response;

    @HttpTest(method = Method.GET, path = "/not-existing-url")
    public void when_resource_not_existing_it_responds_404() {
        assertNotFound(response);
    }

    @HttpTest(method = Method.PUT, path = "/adverts")
    public void when_http_method_is_not_implemented_it_responds() {
        assertMethodNotAllowed(response);
    }

    @HttpTest(method = Method.GET, path = "/adverts")
    public void when_resource_does_exists_it_responds() {
        assertOk(response);
    }

    @HttpTest(method = Method.GET, path = "/adverts?page=0&size=5")
    public void when_resource_with_params_does_exists_it_responds() {
        assertOk(response);
    }

    @HttpTest(method = Method.GET, path = "/adverts/4")
    public void when_is_requested_a_single_resource_it_responds() {
        assertOk(response);
    }

    @HttpTest(method = Method.GET, path = "/adverts/1000")
    public void when_is_requested_a_not_existing_single_resource_it_responds() {
        assertNotFound(response);
    }

}