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
        try {
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);

            return OAuthResponseStrategy.instance(oauthRequest).respond();

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

}
