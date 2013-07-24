package jobengine.application;

import jobengine.domain.Advert;
import jobengine.domain.Adverts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/adverts")
class ApiAdverts {

    private Logger logger = LoggerFactory.getLogger(ApiAdverts.class);
    @Autowired
    private Adverts adverts;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Advert> index(String what, String where) {
        logger.debug("api|GET /adverts");
        return adverts.search(what, where);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public Advert show(@PathVariable Long id) {
        logger.debug("api|GET /adverts/{}", id);
//        RestPreconditions.checkNotNull( id );
        return adverts.viewAdvert(id);
    }

}