package jobengine.app.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.Iterator;

@Provider
@PreMatching
public class HeaderRequestFilter implements ContainerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(HeaderRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logDebugInformationsOn(requestContext);
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
