package jobengine.oauth;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
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
        System.out.printf("realm: %s, token: %s", realm, token, request.getUserPrincipal());
        logger.debug("realm: %s, token: %s", realm, token);
    }

    @Override
    public boolean isAuthorized() {
        try {
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.HEADER);

            return ClientOAuthInfo.ACCESS_TOKEN_VALID.equals(oauthRequest.getAccessToken());
        } catch (Exception e) {
            logger.error("Error while verifying OAuth access token", e);
            return false;
        }
    }

    @Override
    public Principal getPrincipal() {
        return request.getUserPrincipal();
    }

    @Override
    public OAuthClient getOAuthClient() {
        return new OAuthClient() {
            public String getClientId() {  return "test_id";  }
        };
    }
}