package jobengine.app.request;

import com.google.common.collect.Lists;
import jobengine.app.ex.NotAcceptableRequest;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Versions {

    private static final String DEFAULT_VERSION = "v1";

    private List<String> versions;

    public Versions(List<String> accept) {
        this.versions = toVersionsFromHeader(accept);
    }

    public String extract() {
        if (moreThanOneVersionSpecified()) throw new NotAcceptableRequest();

        if (noVersionSpecified()) return DEFAULT_VERSION;

        return versions.get(0);
    }

    private List<String> toVersionsFromHeader(List<String> accept) {
        versions = Lists.newArrayList();

        for (MediaType media : mediaSubtypeListFrom(accept)) {
            addToVersionsIfIsMatchedOn(media.getSubtype());
        }

        return versions;
    }

    private void addToVersionsIfIsMatchedOn(String mediaSubtype) {
        Matcher versionMatcher = versionMatcherOf(mediaSubtype);

        if (versionMatcher.matches())
            versions.add(versionMatcher.group(1));
    }

    public boolean noVersionSpecified() {
        return versions.isEmpty();
    }

    public boolean moreThanOneVersionSpecified() {
        return versions.size() > 1;
    }

    public static List<MediaType> mediaSubtypeListFrom(List<String> accepts) {
        List<MediaType> mediaTypes = Lists.newArrayList();

        for (String accept : accepts)
            mediaTypes.addAll(MediaType.parseMediaTypes(accept));

        return mediaTypes;
    }

    public static Matcher versionMatcherOf(String mediaSubtype) {
        return Pattern.compile("vnd\\.jobrapido\\.(.*)\\+(.*)").matcher(mediaSubtype);
    }

}