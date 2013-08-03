package jobengine.app;

import jobengine.domain.Advert;
import jobengine.domain.Adverts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Scope("prototype")
@Path("/adverts")
@Produces(MediaType.APPLICATION_JSON)
public class ApiAdverts {

    private Logger logger = LoggerFactory.getLogger(ApiAdverts.class);

    @Autowired
    private Adverts adverts;

    @GET
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
