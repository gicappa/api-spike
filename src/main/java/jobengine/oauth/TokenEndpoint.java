package jobengine.oauth;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/token")
public class TokenEndpoint {

    public static final String TOKEN_EXPIRATION_TIME_SEC = "3600";

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response authorize(@Context HttpServletRequest request) throws OAuthSystemException {

        OAuthTokenRequest oauthRequest;

        try {
            oauthRequest = new OAuthTokenRequest(request);

            OAuthResponseStrategy responseStrategy = OAuthResponseStrategy.instance(oauthRequest);

            return responseStrategy.respond();

        } catch (OAuthProblemException e) {
            OAuthResponse response = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e)
                    .buildJSONMessage();
            return respond(response);
        }
    }

    private Response respond(OAuthResponse response) {
        return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }

    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response authorizeGet(@Context HttpServletRequest request) throws OAuthSystemException {
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

        OAuthResponse response = OAuthASResponse
                .tokenResponse(HttpServletResponse.SC_OK)
                .setAccessToken(oauthIssuerImpl.accessToken())
                .setExpiresIn(TOKEN_EXPIRATION_TIME_SEC)
                .buildJSONMessage();

        return respond(response);
    }

    public static abstract class OAuthResponseStrategy {
        protected OAuthTokenRequest oauthRequest;

        public OAuthResponseStrategy(OAuthTokenRequest oauthRequest) {
            this.oauthRequest = oauthRequest;
        }

        public Response respond() throws OAuthSystemException {
            if (!valid())
                return errorResponse();

            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

            return respond(OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(oauthIssuerImpl.accessToken())
                    .setExpiresIn("3600")
                    .buildJSONMessage());

        }

        public abstract boolean valid();

        public abstract Response errorResponse() throws OAuthSystemException;


        public static OAuthResponseStrategy instance(OAuthTokenRequest request) {
            if (wrongClientId(request)) {
                return new ClientIdStrategy(request);
            } else if (isAuthCode(request)) {
                return new AuthCodeStrategy(request);
            } else if (isUserPassword(request)) {
                return new UsernamePasswordStrategy(request);
            } else if (isRefreshToken(request)) {
                return new RefreshStrategy(request);
            }
            throw new RuntimeException("Not recognised GrantType");
        }

        protected Response respond(OAuthResponse response) {
            return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
        }

        protected OAuthResponse invalidGrant(String errorDescription) throws OAuthSystemException {
            return OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(OAuthError.TokenResponse.INVALID_GRANT)
                    .setErrorDescription(errorDescription)
                    .buildJSONMessage();
        }

        protected OAuthResponse invalidClient(String errorDescription) throws OAuthSystemException {
            return OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                    .setErrorDescription(errorDescription)
                    .buildJSONMessage();
        }

        private static boolean isRefreshToken(OAuthTokenRequest request) {
            return request.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.REFRESH_TOKEN.toString());
        }

        private static boolean isUserPassword(OAuthTokenRequest request) {
            return request.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.PASSWORD.toString());
        }

        private static boolean isAuthCode(OAuthTokenRequest request) {
            return request.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString());
        }

        private static boolean wrongClientId(OAuthTokenRequest request) {
            return !ClientOAuthInfo.CLIENT_ID.equals(request.getParam(OAuth.OAUTH_CLIENT_ID));
        }
    }

    public static class ClientIdStrategy extends OAuthResponseStrategy {
        public ClientIdStrategy(OAuthTokenRequest oauthRequest) {
            super(oauthRequest);
        }

        public boolean valid() {
            return false;
        }

        public Response errorResponse() throws OAuthSystemException {
            return respond(invalidClient("client_id not found"));
        }
    }

    public static class UsernamePasswordStrategy extends OAuthResponseStrategy {
        public UsernamePasswordStrategy(OAuthTokenRequest oauthRequest) {
            super(oauthRequest);
        }

        public boolean valid() {
            return oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.PASSWORD.toString());
        }

        public Response errorResponse() throws OAuthSystemException {
            return respond(invalidGrant("invalid authorization code"));
        }

    }

    public static class AuthCodeStrategy extends OAuthResponseStrategy {
        public AuthCodeStrategy(OAuthTokenRequest oauthRequest) {
            super(oauthRequest);
        }

        public boolean valid() {
            return ClientOAuthInfo.PASSWORD.equals(oauthRequest.getPassword())
                    || !ClientOAuthInfo.USERNAME.equals(oauthRequest.getUsername());
        }

        public Response errorResponse() throws OAuthSystemException {
            return respond(invalidGrant("invalid username or password"));
        }
    }

    public static class RefreshStrategy extends OAuthResponseStrategy {
        public RefreshStrategy(OAuthTokenRequest oauthRequest) {
            super(oauthRequest);
        }

        public boolean valid() {
            return false;
        }

        public Response errorResponse() throws OAuthSystemException {
            return respond(invalidGrant("invalid username or password"));
        }
    }
}
