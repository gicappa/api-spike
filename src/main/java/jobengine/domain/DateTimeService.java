package jobengine.domain;

import java.util.Date;

public class DateTimeService {
    public String writeTime() {
        return new Date().toString();
    }
}
