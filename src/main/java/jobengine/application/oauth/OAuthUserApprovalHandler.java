package jobengine.application.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

/**
 * @author Dave Syer
 */
public class OAuthUserApprovalHandler extends TokenServicesUserApprovalHandler {

    private AuthorizationRequest authorizationRequest;

    /**
     * Allows automatic approval for a white list of clients in the implicit grant case.
     *
     * @param authorizationRequest The authorization request.
     * @param userAuthentication   the current user authentication
     * @return Whether the specified request has been approved by the current user.
     */
    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {

        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }

        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        this.authorizationRequest = authorizationRequest;
        return hasOAUTHApprovedParam() || requestContainsToken();
    }

    private boolean hasOAUTHApprovedParam() {
        return approvalParam() != null && approvalParam().toLowerCase().equals("true");
    }

    private String approvalParam() {
        return authorizationRequest.getApprovalParameters().get(AuthorizationRequest.USER_OAUTH_APPROVAL);
    }

    private boolean requestContainsToken() {
        return this.authorizationRequest.getResponseTypes().contains("token");
    }

}
