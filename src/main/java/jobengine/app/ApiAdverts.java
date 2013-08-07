package jobengine.app;

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
public class ApiAdverts {

    private Logger logger = LoggerFactory.getLogger(ApiAdverts.class);
    @Autowired
    private Adverts adverts;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Advert> index_v1() {
        logger.debug("api|v1|GET /adverts");
        return adverts.search("", "");
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<Advert> index_alpha(String what, String where) {
//        logger.debug("api|alpha|GET /adverts");
//        return adverts.search(what, where);
//    }

    @GET
    @Path("/{id}")
    public Advert show(@PathParam("id") Long id) {
        logger.debug("api|GET /adverts/{}", id);
        return adverts.viewAdvert(id);
    }
}
