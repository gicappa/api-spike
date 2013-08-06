package jobengine.domain;

import java.io.Serializable;

public class Advert implements Serializable {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    private final Long id;
    private final String url;

    public Advert(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }
}
