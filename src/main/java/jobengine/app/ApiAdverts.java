package jobengine.app;

import com.wordnik.swagger.annotations.*;
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
    @ApiOperation(value = "fetch all the adverts", notes = "Add extra notes here", response = List.class)
    public List<Advert> index_v1() {
        logger.debug("api|v1|GET /adverts");
        return adverts.search("", "");
    }

    @GET
    @ApiOperation(value = "fetch all the adverts", notes = "Add extra notes here", response = List.class)
    @Produces({MediaType.APPLICATION_JSON, "application/vnd.jobrapido.alpha+json"})
    @Consumes({MediaType.APPLICATION_JSON, "application/vnd.jobrapido.alpha+json"})
    public List<Advert> index_alpha() {
        logger.debug("api|alpha|GET /adverts");
        return adverts.search_alpha("", "");
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "fetch all the adverts", notes = "Add extra notes here", response = Advert.class)
    public Advert show(@ApiParam(value = "ID of pet to fetch", required = true) @PathParam("id") Long id) {
        logger.debug("api|GET /adverts/{}", id);
        return adverts.viewAdvert(id);
    }
}
