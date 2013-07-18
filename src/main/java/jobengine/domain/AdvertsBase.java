package jobengine.domain;

import java.util.List;


public interface AdvertsBase {
    List<Advert> find();

    Advert find(Long id);
}
