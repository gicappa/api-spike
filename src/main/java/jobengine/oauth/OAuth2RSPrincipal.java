package jobengine.oauth;

import java.security.Principal;

public class OAuth2RSPrincipal implements Principal {

    private String userIdentifier;

    public OAuth2RSPrincipal(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    @Override
    public String getName() {
        return userIdentifier;
    }
}