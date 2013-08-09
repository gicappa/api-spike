package jobengine.app.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable t) {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(t.getMessage()).build();
    }

}