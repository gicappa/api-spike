package jobengine.oauth;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.rsfilter.OAuthDecision;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuthRSProvider implements org.apache.oltu.oauth2.rsfilter.OAuthRSProvider {
    private HttpServletResponse httpServletResponse;

    @Override
    public OAuthDecision validateRequest(String realm, String token, HttpServletRequest request)
            throws OAuthProblemException {
        return new OAuth2RSDecision(realm, token, request);
    }

    public void setServletResponse(HttpServletResponse response) {
        httpServletResponse = response;
    }
}
