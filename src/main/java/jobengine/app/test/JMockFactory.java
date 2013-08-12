package jobengine.app.test;

import jobengine.domain.Adverts;
import org.jmock.Mockery;

public class JMockFactory {

    private final Mockery context;

    public JMockFactory(Mockery context) {
        this.context = context;
    }

    public Adverts mockAdverts() {
        return context.mock(Adverts.class);
    }
}
