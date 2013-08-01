package jobengine.app.request;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.util.Collections;

public class JobrapidoVndConverter extends MappingJacksonHttpMessageConverter {

    public JobrapidoVndConverter() {
        setSupportedMediaTypes(Collections.singletonList(json()));
    }

    private MediaType json() {
        return MediaType.parseMediaType("application/vnd.jobrapido.v1+json");
    }
}
