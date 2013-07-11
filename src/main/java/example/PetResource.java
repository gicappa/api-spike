package example;

import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;

@Path("/pet")
@Api(value = "/pet", description = "Operations about pets")
@Produces({"application/json"})
public class PetResource {
    @GET
    @Path("/{petId}")
    @ApiOperation(value = "Find pet by ID", notes = "Add extra notes here", responseClass = "com.wordnik.swagger.sample.model.Pet")
    @ApiErrors(value = {@ApiError(code = 400, reason = "Invalid ID supplied"),
            @ApiError(code = 404, reason = "Pet not found")})
    public String getPetById(
            @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,5]", required = true) @PathParam("petId") String petId)
            throws NotFoundException {
        return "";
    }
}