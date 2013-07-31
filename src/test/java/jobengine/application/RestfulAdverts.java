package jobengine.application;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import jobengine.application.utils.WebServer;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.*;

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

    @Ignore("there is a restfuse issue on this [https://github.com/eclipsesource/restfuse/issues/42]")
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