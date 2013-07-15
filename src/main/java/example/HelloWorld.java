package example;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import jobengine.domain.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
@Api(value = "/helloworld", description = "Just say hi!")
@Produces({"application/json"})
@Component
public class HelloWorld {

    @Autowired
    public DateTimeService dateTimeService;

    @GET
    @Path("/hi")
    @ApiOperation(value = "Say hi", notes = "Add extra notes here")
    public String hi() {
        return "Hello";
    }

    @GET
    @Path("/time")
    public String time() {
        assert dateTimeService != null;
        return dateTimeService.writeTime();
    }
}