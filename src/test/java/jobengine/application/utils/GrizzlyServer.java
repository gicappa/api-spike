package jobengine.application.utils;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.rules.ExternalResource;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.createHttpServer;

public class GrizzlyServer extends ExternalResource {

    private HttpServer server;

    public GrizzlyServer(Class servlet) {
        Logger.getLogger("org.glassfish").setLevel(Level.OFF);
        this.server = createHttpServer(uri(), conf(servlet));
    }

    private ResourceConfig conf(Class servlet) {
        return new ResourceConfig(servlet);
    }

    protected void before() throws Throwable {
        out.println("starting webserver...");
        server.start();
    }

    protected void after() {
        try {
            server.stop();
            out.println("...stopped webserver");
        } catch (Exception e) {
            err.println("problem while stopping webserver");
        }
    }

    private URI uri() {
        return URI.create("http://localhost:8080/api-spike/");
    }

}
