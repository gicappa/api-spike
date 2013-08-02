package jobengine.app.request;

import com.google.common.collect.ImmutableList;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class JobrapidoVndConverter extends MappingJacksonHttpMessageConverter {

    public JobrapidoVndConverter() {
        setSupportedMediaTypes(ImmutableList.of(json_v1(), json_alpha()));
    }

    private MediaType json_v1() {
        return MediaType.parseMediaType("application/vnd.jobrapido.v1+json");
    }

    private MediaType json_alpha() {
        return MediaType.parseMediaType("application/vnd.jobrapido.alpha+json");
    }
}
