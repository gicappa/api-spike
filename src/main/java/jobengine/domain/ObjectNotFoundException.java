package jobengine.domain;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }
}
