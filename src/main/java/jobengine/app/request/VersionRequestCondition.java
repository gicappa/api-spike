package jobengine.app.request;

import jobengine.app.ex.NotAcceptableRequest;
import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;


public final class VersionRequestCondition extends AbstractRequestCondition<VersionRequestCondition> {

    private static final String JOBRAPIDO_MEDIA_TYPE = "X-Jobrapido-Media-Type";

    private TreeSet<String> versions;

    public VersionRequestCondition(String version) {
        versions = new TreeSet<String>();
        versions.add(version);
    }

    public VersionRequestCondition combine(VersionRequestCondition other) {
        if (!this.versions.first().equals(other.versions.first()))
            throw new NotAcceptableRequest("Version mistmatch!");

        return this;
    }

    public VersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        for (String version: versions) {
            if (match(version, request)) {
                return null;
            }
        }
        return this;
    }

    private boolean match(String version, HttpServletRequest request) {
        return version.equals(request.getAttribute(JOBRAPIDO_MEDIA_TYPE));
    }

    public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
        if (this.versions.first().equals(other.versions.first()))
            return 0;

        return -1;
    }

    protected Collection<String> getContent() {
        return Collections.synchronizedSet(versions) ;
    }

    protected String getToStringInfix() {
        return "|";
    }
}
