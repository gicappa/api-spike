package jobengine.app;

import jobengine.domain.Advert;
import jobengine.domain.Adverts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class ApiAdverts {

    private Logger logger = LoggerFactory.getLogger(ApiAdverts.class);
    @Autowired
    private Adverts adverts;

    @ResponseBody
    public List<Advert> index_v1(String what, String where) {
        logger.debug("api|v1|GET /adverts");
        return adverts.search(what, where);
    }

    @ResponseBody
    public List<Advert> index_alpha(String what, String where) {
        logger.debug("api|alpha|GET /adverts");
        return adverts.search(what, where);
    }

    @ResponseBody
    public Advert show(@PathVariable Long id) {
        logger.debug("api|GET /adverts/{}", id);
        return adverts.viewAdvert(id);
    }
}
