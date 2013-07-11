package example;

//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//import search.SearchAPI;
//
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import java.io.IOException;
//import java.net.URI;
//import java.util.logging.Level;
//import java.util.logging.Logger;

@Path("/helloworld")
public class HelloWorld {
//    public static void main(String[] args) throws IOException {
//
//        final URI BASE_URI = URI.create("http://localhost:8080/base/");
//        final String ROOT_PATH = "helloworld";
//
//
//        try {
//            System.out.println("\"Hello World\" Jersey Example App");
//
//            final ResourceConfig resourceConfig = new ResourceConfig(HelloWorld.class, SearchAPI.class);
//            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
//
//            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...", BASE_URI, ROOT_PATH));
//            System.in.read();
//            server.stop();
//        } catch (IOException ex) {
//            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello World";
    }
}