package jobengine.infrastructure;

import jobengine.domain.Advert;
import jobengine.domain.AdvertsBase;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AdvertPersistence implements AdvertsBase {
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
        return find().get(id.intValue());
    }
}