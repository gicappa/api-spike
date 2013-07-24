package jobengine.domain;

import java.util.List;

public interface AdvertsPersistence {
    List<Advert> find();

    Advert find(Long id);
}
