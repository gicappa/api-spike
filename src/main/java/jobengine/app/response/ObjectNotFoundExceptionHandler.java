package jobengine.app.response;

import jobengine.domain.ObjectNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectNotFoundExceptionHandler implements ExceptionMapper<ObjectNotFoundException> {
    public Response toResponse(ObjectNotFoundException e) {
        return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}