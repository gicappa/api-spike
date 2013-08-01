package jobengine.application;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestHeaderInterceptor extends HandlerInterceptorAdapter {

    public static final String DEFAULT_VERSION = "v1";
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
        List<String> versions = mapMediaTypesToVersions(acceptHeaderIn(request));

        if (moreThanOneSpecified(versions)) throw new NotAcceptableRequest();

        if (noSpecified(versions)) return DEFAULT_VERSION;

        return versions.get(0);
    }

    private boolean noSpecified(List<String> versions) {
        return versions.isEmpty();
    }

    private boolean moreThanOneSpecified(List<String> versions) {
        return versions.size() > 1;
    }

    private List<String> mapMediaTypesToVersions(String accept) {
        List<String > versions = Lists.newArrayList();

        for(MediaType media : mediaSubtypeListOf(accept)) {
            Matcher regexp = regexMatcherOf(media.getSubtype());
            if (regexp.matches()) {
                versions.add(regexp.group(1));
            }
        }
        return versions;
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
