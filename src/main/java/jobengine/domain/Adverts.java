package jobengine.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Adverts {
    List<Advert> search(String what, String where);

    List<Advert> search_alpha(String what, String where);

    List<Advert> search(String what, String where, Integer radius);

    List<Advert> search(String what, String where, Integer radius, Page page);

    Advert viewAdvert(Long id);

    @Component
    public class AdvertsDefault implements Adverts {

        @Autowired
        private AdvertsPersistence advertsPersistence;

        public List<Advert> search(String what, String where) {
            return advertsPersistence.find();
        }

        public List<Advert> search_alpha(String what, String where) {
            return advertsPersistence.find();
        }

        public List<Advert> search(String what, String where, Integer radius) {
            return advertsPersistence.find();
        }

        public List<Advert> search(String what, String where, Integer radius, Page page) {
            return advertsPersistence.find();
        }

        public Advert viewAdvert(Long id) {
            return advertsPersistence.find(id);
        }
    }
}


