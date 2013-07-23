package jobengine.infrastructure;

import jobengine.domain.ObjectNotFound;
import org.junit.Before;
import org.junit.Test;

public class AdvertPersistenceTest {

    AdvertPersistence persistence;

    @Before
    public void before() {
        persistence = new AdvertPersistence();
    }

    @Test(expected = ObjectNotFound.class)
    public void when_find_on_a_not_existing_record_throws_ObjectNotFound_exception() {
        persistence.find(1000l);
    }
}
