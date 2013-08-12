package jobengine.app.response;

import jobengine.app.ex.RestException;
import jobengine.app.ex.RestHttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.google.common.base.Strings.emptyToNull;

@Provider
public class RestExceptionHandler implements ExceptionMapper<RestException> {

    @Override
    public Response toResponse(RestException ex) {
        return Response.status(safeValue(statusAnnotationFrom(ex))).entity(errorMessage(ex)).build();
    }

    public String errorMessage(RestException e) {
        return String.format("%s - %s", safeReason(statusAnnotationFrom(e)), e.getMessage());
    }

    private RestHttpStatus statusAnnotationFrom(RestException e) {
        return e.getClass().getAnnotation(RestHttpStatus.class);
    }

    private Status safeValue(RestHttpStatus status) {
        if (status == null)
            return Status.INTERNAL_SERVER_ERROR;

        return status.value();
    }

    private String safeReason(RestHttpStatus status) {
        return emptyToNull(status.reason());
    }
}