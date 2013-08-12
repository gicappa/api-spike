package jobengine.app;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import jobengine.domain.Advert;
import jobengine.domain.Adverts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/adverts")
@Api(value = "/adverts", description = "adverts description")
@Produces({MediaType.APPLICATION_JSON, "application/vnd.jobrapido.v1+json"})
@Consumes({MediaType.APPLICATION_JSON, "application/vnd.jobrapido.v1+json"})
public class ApiAdverts {

    @Autowired
    private Adverts adverts;

    private Logger logger = LoggerFactory.getLogger(ApiAdverts.class);

    @GET
    @ApiOperation(value = "fetch all the adverts!", notes = "Add extra notes here")
    @ApiResponses({@ApiResponse(code = 200, response = List.class, message = "index_v1")})
    public List<Advert> index_v1() {
        logger.debug("api|v1|GET /adverts");
        return adverts.search("", "");
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, "application/vnd.jobrapido.alpha+json"})
    @Consumes({MediaType.APPLICATION_JSON, "application/vnd.jobrapido.alpha+json"})
    public List<Advert> index_alpha() {
        logger.debug("api|alpha|GET /adverts");
        return adverts.search_alpha("", "");
    }

    @GET
    @Path("/{id}")
    public Advert show(@PathParam("id") Long id) {
        logger.debug("api|GET /adverts/{}", id);
        return adverts.viewAdvert(id);
    }
}
