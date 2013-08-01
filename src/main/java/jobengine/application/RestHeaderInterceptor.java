package jobengine.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
        response.setContentType("application/json;charset=utf-8");
        response.addHeader("X-Jobrapido-Media-Type", extractVersionFrom(request));

        return true;
    }

    private String extractVersionFrom(HttpServletRequest request) {
        String accept = acceptHeaderIn(request);
        String version = "v1";
        boolean matched = false;
        for (MediaType media : mediaSubtypeListOf(accept)) {
            Matcher regexp = regexMatcherOf(media.getSubtype());

            if (regexp.matches() && matched) {
                throw new NotAcceptableRequest();
            }
            if (regexp.matches()) {
                version = regexp.group(1);
                matched = true;
            }
        }
        return version;
    }

    private String extractVersionFrom(Matcher m) {
        return m.matches() ? m.group(1) : "v1";
    }

    private Matcher regexMatcherOf(String mediaSubtype) {
        return Pattern.compile("vnd\\.jobrapido\\.(.*)\\+(.*)").matcher(mediaSubtype);
    }

    private List<MediaType> mediaSubtypeListOf(String accept) {
        return MediaType.parseMediaTypes(accept);
    }

    private String acceptHeaderIn(HttpServletRequest request) {
        return request.getHeader("Accept") == null ? "~/~" : request.getHeader("Accept");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        response.setContentType("application/json;charset=utf-8");
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
