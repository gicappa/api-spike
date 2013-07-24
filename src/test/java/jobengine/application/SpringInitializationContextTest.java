package jobengine.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-rest.xml")
public class SpringInitializationContextTest {

    @Test
    public void whenSpringContextIsInstantiated_thenNoExceptions() { }
}