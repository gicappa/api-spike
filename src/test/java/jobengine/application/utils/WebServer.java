package jobengine.application.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.rules.ExternalResource;

import static java.lang.System.*;

public class WebServer extends ExternalResource {

    private Server server;

    public WebServer() {
        try {
            server = new Server(8080);
            server.setHandler(createWebAppContext());
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            exit(-1);
        }
    }

    private WebAppContext createWebAppContext() {
        WebAppContext wac = new WebAppContext();
        wac.setResourceBase("src/main/webapp");
        wac.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        wac.setContextPath("/");
        wac.setParentLoaderPriority(true);
        return wac;
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
}
