package jobengine.application;

import jobengine.domain.Advert;
import jobengine.domain.Adverts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping( value = "/adverts" )
class ApiAdverts {

    @Autowired
    private Adverts adverts;

    @RequestMapping( method = RequestMethod.GET )
    @ResponseBody
    public List<Advert> search(String what, String where) {
        return adverts.search(what, where);
    }

}
