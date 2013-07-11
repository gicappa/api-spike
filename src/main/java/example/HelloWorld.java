package example;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
@Api(value = "/helloworld", description = "Just say hi!")
@Produces({"application/json"})
public class HelloWorld {

    @GET
    @Path("/hi")
    @ApiOperation(value = "Say hi", notes = "Add extra notes here")

    public String hi() {
        return "Hello";
    }
}