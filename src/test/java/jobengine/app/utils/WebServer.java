package jobengine.app.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.rules.ExternalResource;

import static java.lang.System.exit;
import static java.lang.System.out;

public class WebServer extends ExternalResource {

    private Server server;

    public WebServer() {
        try {
            server = new Server(53280);
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
        wac.setExtraClasspath("src/main/resources,src/test/resources");
        wac.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        wac.setContextPath("/");
        wac.setParentLoaderPriority(true);
        return wac;
    }

    protected void before() {
        out.print("starting webserver...");
        try {
            server.start();
            out.println("                    [OK]");
        } catch (Exception e) {
            out.println("                    [FAIL]");
        }
    }

    protected void after() {
        out.print("stopping webserver...");
        try {
            server.stop();
            out.println("                    [OK]");
        } catch (Exception e) {
            out.println("                    [FAIL]");
        }
    }
}
