package jobengine.domain;

public class ObjectNotFound extends RuntimeException {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public ObjectNotFound() {
        super();
    }

    public ObjectNotFound(String message) {
        super(message);
    }

    public ObjectNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFound(Throwable cause) {
        super(cause);
    }
}
