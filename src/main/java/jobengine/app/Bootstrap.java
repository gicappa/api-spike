package jobengine.app;

import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.jaxrs.config.ReflectiveJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

import javax.servlet.http.HttpServlet;


public class Bootstrap extends HttpServlet {
    static {
//        ScannerFactory.setScanner(new ReflectiveJaxrsScanner());
        ClassReaders.setReader(new DefaultJaxrsApiReader());
    }

}
