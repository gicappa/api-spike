package jobengine.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableRequest extends RuntimeException {

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
    }}
