package jobengine.app.ex;

import javax.ws.rs.core.Response;

@RestHttpStatus(value = Response.Status.BAD_REQUEST)
@SuppressWarnings("unused")
public class BadRequestException extends RestException {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
