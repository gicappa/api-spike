package jobengine.app.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

@Provider
@PreMatching
public class HeaderRequestFilter implements ContainerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(HeaderRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logDebugInformationsOn(requestContext);
        requestContext.setProperty("version", extractVersionFrom(requestContext));
    }

    private String extractVersionFrom(ContainerRequestContext request) {
        return new Versions(acceptHeaderIn(request)).extract();
    }

    private List<String> acceptHeaderIn(ContainerRequestContext request) {
        return request.getHeaders().get("Accept") == null ? asList("~/~") : request.getHeaders().get("Accept");
    }


    private void logDebugInformationsOn(ContainerRequestContext request) {
        if (logger.isDebugEnabled()) {
            logger.debug("decorating response headers");

            Iterator<MediaType> mediaType = request.getAcceptableMediaTypes().iterator();
            if (!mediaType.hasNext()) {
                logger.debug(String.format("accepting %s", mediaType.next()));
            }
        }
    }
}
