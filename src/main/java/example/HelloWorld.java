package example;

import com.wordnik.swagger.annotations.Api;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import search.SearchAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
@Api(value = "/helloworld", description = "Just say hi!")
public class HelloWorld {
    public static void main(String[] args) throws IOException {

        final URI BASE_URI = URI.create("http://localhost:8080/base/");
        final String ROOT_PATH = "helloworld";


        try {
            System.out.println("\"Hello World\" Jersey Example App");

            final ResourceConfig resourceConfig = new ResourceConfig(HelloWorld.class, SearchAPI.class);
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);

            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...",
                    BASE_URI, ROOT_PATH));
            System.in.read();
            server.stop();
        } catch (IOException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }
}