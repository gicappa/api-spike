package example;

import com.wordnik.swagger.jaxrs.JaxrsApiReader;

import javax.servlet.http.HttpServlet;

public class Bootstrap extends HttpServlet {
    static {
        JaxrsApiReader.setFormatString("");
    }
}
