package jobengine.app;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.jaxrs.listing.ApiListing;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/docs")
@Api("/docs")
@Produces({"application/json"})
public class ApiListingResourceJSON extends ApiListing {}