package jobengine.app.ex;

import javax.ws.rs.core.Response;

@RestHttpStatus(value = Response.Status.NOT_ACCEPTABLE)
@SuppressWarnings("unused")
public class NotAcceptableRequest extends RestException {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public NotAcceptableRequest() {
        super();
    }

    public NotAcceptableRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAcceptableRequest(String message) {
        super(message);
    }

    public NotAcceptableRequest(Throwable cause) {
        super(cause);
    }
}
