package jobengine.app.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SpringInitializationContextTest {

    @Test
    public void whenSpringContextIsInstantiated_thenNoExceptions() {
    }
}
