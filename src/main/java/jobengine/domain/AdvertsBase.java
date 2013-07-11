package jobengine.domain;

import java.util.Arrays;
import java.util.List;

public interface AdvertsBase {

    List<Advert> find();
    Advert find(Long id);

    public class AdvertBaseImpl implements AdvertsBase {
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

}
