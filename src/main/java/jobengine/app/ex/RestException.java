package jobengine.app.ex;


@SuppressWarnings("unused")
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public RestException() {
        super();
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(Throwable cause) {
        super(cause);
    }
}
