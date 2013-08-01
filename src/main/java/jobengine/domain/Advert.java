package jobengine.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Advert {

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
