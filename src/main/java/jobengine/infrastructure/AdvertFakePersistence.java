package jobengine.infrastructure;

import jobengine.domain.Advert;
import jobengine.domain.AdvertsPersistence;
import jobengine.domain.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Component
public class AdvertFakePersistence implements AdvertsPersistence {
    @Override
    public List<Advert> find() {
        return Arrays.asList(
                new Advert(1l, "http://www.monster.it/url1"),
                new Advert(2l, "http://www.monster.it/url2"),
                new Advert(3l, "http://www.monster.it/url3"),
                new Advert(4l, "http://www.monster.it/url4"),
                new Advert(5l, "http://www.monster.it/url5"),
                new Advert(6l, "http://www.monster.it/url6")
        );
    }

    @Override
    public Advert find(Long id) {
        try {
            return find().get(id.intValue());
        } catch (Throwable t) {
            throw new ObjectNotFoundException(format("Advert[id: %d] not existent", id), t);
        }
    }
}