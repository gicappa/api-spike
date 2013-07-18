package jobengine.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Adverts {
    List<Advert> search(String what, String where);
    List<Advert> search(String what, String where, Integer radius);
    List<Advert> search(String what, String where, Integer radius, Page page);
    Advert viewAdvert(Long id);

    @Component
    public class AdvertsDefault implements Adverts {

        @Autowired
        private AdvertsBase advertsBase;

        public List<Advert> search(String what, String where) {
            return advertsBase.find();
        }

        public List<Advert> search(String what, String where, Integer radius) {
            return advertsBase.find();
        }

        public List<Advert> search(String what, String where, Integer radius, Page page) {
            return advertsBase.find();
        }

        public Advert viewAdvert(Long id) {
            return advertsBase.find(id);
        }
    }
}


