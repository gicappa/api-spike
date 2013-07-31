package jobengine.application;

import com.google.common.base.Function;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestHeaderInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(RestHeaderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        logDebugInformationsOn(request);

        response.setHeader("Server", "Jobrapido");
        response.addHeader("Access-Control-Allow-Origin", "*");

        response.setDateHeader("Date", new Date().getTime());
        String accept = request.getHeader("Accept");
        response.setContentType("application/json;charset=utf-8");

        if (accept == null ) {
            response.addHeader("X-Jobrapido-Media-Type", "v1");
            return true;
        }
        Pattern p = Pattern.compile("vnd\\.jobrapido\\.(.*)\\+(.*)");
        Matcher m = p.matcher(MediaType.parseMediaType(accept).getSubtype());
        if (accept == null || m.matches()) {
            String version = m.group(1);
            System.out.println(version);
            response.addHeader("X-Jobrapido-Media-Type", version);
        } else {
            response.addHeader("X-Jobrapido-Media-Type", "v1");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        response.setContentType("application/json;charset=utf-8");
    }

    class Pippo implements Function<String, String> {

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
