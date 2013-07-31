package jobengine.application;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jobengine.application.utils.WebServer;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;


//@RunWith(HttpJUnitRunner.class)
public class OAuth2Test {

    @ClassRule
    public static WebServer g = new WebServer();
    @Rule
    public Destination d = new Destination(this, "http://localhost:8080/rest/");
    @Context
    private Response response;

    @Ignore("WIP")
    @HttpTest(method = Method.POST, path = "/oauth/token?client_secret=secret&grant_type=password&username=demo&password=1234")
    public void it_return_a_token_when_asked_for_it() {
        AccessToken token = gson().fromJson(response.getBody(), AccessToken.class);

        assertThat(token.accessToken, is(not(null)));
    }

    private Gson gson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    class AccessToken {
        public String accessToken;
        public String tokenType;
        public String refreshToken;
        public int expiresIn;
    }
}
