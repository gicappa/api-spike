package jobengine.application;

import com.google.common.base.Function;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class RestHeaderInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(RestHeaderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        logDebugInformationsOn(request);

        response.setHeader("Server", "Jobrapido");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("X-Jobrapido-Media-Type", "v1");

        response.setDateHeader("Date", new Date().getTime());
        String accept = request.getHeader("Accept");

        if (accept == null) return true;

        MediaType mediaType = MediaType.parseMediaType(accept);
        System.out.println(mediaType.getSubtype());
        //("vnd.jobrapido.alpha+json")

        return true;
    }


    class Pippo implements Function<String , String> {

        @Override
        public String apply(@Nullable java.lang.String input) {
            return null;
        }

        @Override
        public boolean equals(@Nullable java.lang.Object object) {
            return false;
        }
    }

    private void logDebugInformationsOn(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("decorating response headers");
            if (!request.getHeaders("Accept").hasMoreElements()) {
                logger.debug(String.format("accepting %s", request.getHeaders("Accept").toString()));
            }
        }
    }


}
