package jobengine.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Advert")
public class Advert implements Serializable {

    private static final long serialVersionUID = 42l; // Life, the universe, and everything

    public final Long id;
    public final String url;

    public Advert() {
        this.id = -1l;
        this.url = "";
    }

    public Advert(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    @XmlElement(name = "url")
    public String getUrl() {
        return url;
    }

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }
}
