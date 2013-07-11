package jobengine.application.api;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import jobengine.application.utils.GrizzlyServer;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.assertOk;

@RunWith(HttpJUnitRunner.class)
public class RestfulAdverts {

    @ClassRule public static GrizzlyServer g = new GrizzlyServer(Adverts.class);
    @Rule public Destination d = new Destination(this, "http://localhost:8080/api-spike/");
    @Context private Response response;

    @HttpTest(method = Method.GET, path = "/adverts")
    public void checkRestfuseOnlineStatus() {
        assertOk(response);
    }

}