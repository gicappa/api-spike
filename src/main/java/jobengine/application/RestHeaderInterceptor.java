package jobengine.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class RestHeaderInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(RestHeaderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        logger.debug("decorating response headers");
        response.setHeader("Server", "Jobrapido");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setDateHeader("Date", new Date().getTime());
        if (!request.getHeaders("Accept").hasMoreElements()) {
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
