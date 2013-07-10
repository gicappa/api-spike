package search;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class SearchResult {
    private String id;
    private String name;
    private List<Advert> adverts;
    private String title;

    public SearchResult() {}

    public SearchResult(String id, String name, List<Advert> adverts) {
        this.id = id;
        this.name = name;
        this.adverts = adverts;
    }

    @XmlElement(name="adverts")
    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }
}
