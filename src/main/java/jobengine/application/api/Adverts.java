package jobengine.application.api;

import jobengine.domain.Advert;
import jobengine.domain.AdvertsBase;
import jobengine.domain.ApiAdverts;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
public class Adverts {

    @Inject
    private ApiAdverts adverts;


    public List<Advert> search(String what, String where) {
        throw new NotSupportedException("not yet implemented");
    }

}
