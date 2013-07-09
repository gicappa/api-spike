package jobengine.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
    static enum Status {
        ok, ko, unauth
    }

    public Status status;

    @XmlElement
    public String Name;

    public Object payload;
}
