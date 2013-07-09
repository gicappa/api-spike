package jobengine.api;

public class Result {
    static enum Status {
        ok, ko, unauth
    }

    public Status status;

    public Object payload;
}
