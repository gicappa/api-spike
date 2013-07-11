package jobengine.application.api;

import jobengine.domain.Advert;
import jobengine.domain.AdvertsBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/adverts")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class Adverts {

    private AdvertsBase advertsBase;

    public Adverts() {
        advertsBase = new AdvertsBase.AdvertBaseImpl();
    }

    @GET
    public List<Advert> index() {
        return advertsBase.find();
    }

    @GET
    @Path("{id}")
    public void show(@PathParam("id") String id) {

    }

    @POST
    public void create() {

    }

    @PUT
    public void update() {

    }

    @DELETE
    public void delete() {

    }


}
