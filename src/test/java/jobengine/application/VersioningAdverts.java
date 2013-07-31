package jobengine.application;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.Header;
import com.eclipsesource.restfuse.annotation.HttpTest;
import jobengine.application.utils.WebServer;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

@RunWith(HttpJUnitRunner.class)
public class VersioningAdverts {

    @ClassRule
    public static WebServer g = new WebServer();
    @Rule
    public Destination d = new Destination(this, "http://localhost:8080/api/");
    @Context
    private Response response;

    @HttpTest(method = Method.GET, path = "/adverts")
    public void when_no_accept_is_specified_return_json_last_version() {
        assertThat(response.getHeaders().get("Content-Type"), hasItem("application/json;charset=utf-8"));
        //assertThat(response.getHeaders().get("X-Jobrapido-Media-Type"), hasItem("v1; format=json"));
    }

    @Ignore
    @HttpTest(method = Method.GET, path = "/adverts", headers = {@Header(name = "Accept", value = "application/json")})
    public void when_json_accept_is_specified_return_json_last_version() {
        assertThat(response.getHeaders().get("Content-Type"), hasItem("application/vnd.jobrapido.v1+json"));
    }

    @Ignore
    @HttpTest(method = Method.GET, path = "/adverts", headers = {@Header(name = "Accept", value = "application/vnd.jobrapido.alpha+json")})
    public void when_alpha_json_accept_is_specified_return_json_alpha_version() {
        assertThat(response.getHeaders().get("Content-Type"), hasItem("application/vnd.jobrapido.alpha+json"));
    }

    @Ignore
    @HttpTest(method = Method.GET, path = "/adverts", headers = {@Header(name = "Accept", value = "application/vnd.jobrapido.v1+json")})
    public void when_v1_json_accept_is_specified_return_json_v1_version() {
        assertThat(response.getHeaders().get("Content-Type"), hasItem("application/vnd.jobrapido.v1+json"));
    }
}