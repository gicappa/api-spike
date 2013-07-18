package jobengine.domain;

import java.util.List;

public interface ApiAdverts {
    List<Advert> search(String what, String where);
    List<Advert> search(String what, String where, Integer radius);
    List<Advert> search(String what, String where, Integer radius, Page page);
    Advert viewAdvert(Long id);
}


