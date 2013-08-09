package jobengine.app.ex;

import javax.ws.rs.core.Response;

@RestHttpStatus(value = Response.Status.NOT_FOUND)
@SuppressWarnings("unused")
public class ResourceNotFoundException extends RestException {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}