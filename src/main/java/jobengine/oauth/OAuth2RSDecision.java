package jobengine.oauth;

import org.apache.oltu.oauth2.rsfilter.OAuthClient;
import org.apache.oltu.oauth2.rsfilter.OAuthDecision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class OAuth2RSDecision implements OAuthDecision {

    private String realm;
    private String token;
    private HttpServletRequest request;

    private Logger logger = LoggerFactory.getLogger(OAuth2RSDecision.class);

    public OAuth2RSDecision(String realm, String token, HttpServletRequest request) {
        this.realm = realm;
        this.token = token;
        this.request = request;

        logger.debug("realm: %s, token: %s", realm, token);
    }

    @Override
    public boolean isAuthorized() {
        return checkAuthorization();
    }

    private boolean checkAuthorization() {
        return true;
    }

    @Override
    public Principal getPrincipal() {
        return fetchPrincipal();
    }

    private Principal fetchPrincipal() {
        return new OAuth2RSPrincipal("principal_id");
    }

    @Override
    public OAuthClient getOAuthClient() {
        return new OAuthClient() {
            public String getClientId() {  return "test_id";  }
        };
    }
}