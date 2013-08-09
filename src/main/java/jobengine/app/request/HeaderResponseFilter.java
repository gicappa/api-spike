package jobengine.app.request;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;

@Provider
public class HeaderResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.putSingle("Server", "Jobrapido");
        headers.putSingle("Date", new Date().toString());
        headers.putSingle("Access-Control-Allow-Origin", "*");
        headers.add("X-Jobrapido-Media-Type", extractVersionFrom(requestContext));
    }

    private String extractVersionFrom(ContainerRequestContext request) {
        return new Versions(acceptHeaderIn(request)).extract();
    }

    private String acceptHeaderIn(ContainerRequestContext request) {
        return request.getMediaType() == null ? "~/~" : request.getMediaType().toString();
    }

}