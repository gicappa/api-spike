package jobengine.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:applicationContext.xml")
public class VersioningTest {

    public static final MediaType MEDIA_TYPE_ALPHA = MediaType.parseMediaType("application/vnd.jobrapido.alpha+json");
    public static final MediaType MEDIA_TYPE_V1 = MediaType.parseMediaType("application/vnd.jobrapido.v1+json");
    @Autowired
    private WebApplicationContext wac;
    private MockMvc rest;

    @Before
    public void setup() {
        rest = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        rest.perform(get("/adverts")).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void when_no_media_type_is_specified_return_version_v1() throws Exception {
        rest.perform(get("/adverts")).andExpect(header().string("X-Jobrapido-Media-Type", "v1"));
    }

    @Test
    public void when_media_type_is_json_accept_is_specified_return_json() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void when_json_accept_is_specified_return_version_v1() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.APPLICATION_JSON)).andExpect(header().string("X-Jobrapido-Media-Type", "v1"));
    }

    @Test
    public void when_media_type_is_alpha_json_accept_is_specified_return_json() throws Exception {
        rest.perform(get("/adverts").accept(MEDIA_TYPE_ALPHA)).andExpect(content().contentType("application/json;charset=utf-8"));
    }

    @Test
    public void when_media_type_is_alpha_json_accept_is_specified_return_alpha_version() throws Exception {
        rest.perform(get("/adverts").accept(MEDIA_TYPE_ALPHA)).andExpect(header().string("X-Jobrapido-Media-Type", "alpha"));
    }

    @Test
    public void when_media_type_is_v1_json_accept_is_specified_return_json() throws Exception {
        rest.perform(get("/adverts").accept(MEDIA_TYPE_V1)).andExpect(content().contentType("application/json;charset=utf-8"));
    }

    @Test
    public void when_media_type_is_v1_json_accept_is_specified_return_v1_version() throws Exception {
        rest.perform(get("/adverts").accept(MEDIA_TYPE_V1)).andExpect(header().string("X-Jobrapido-Media-Type", "v1"));
    }

    @Test
    public void when_media_type_is_multiple_it_still_extract_the_version_found() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.TEXT_PLAIN, MEDIA_TYPE_ALPHA)).andExpect(header().string("X-Jobrapido-Media-Type", "alpha"));
    }
    @Test
    public void when_media_types_select_two_version_it_responds_406() throws Exception {
        rest.perform(get("/adverts").accept(MEDIA_TYPE_V1, MEDIA_TYPE_ALPHA)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void xml_is_a_supported_media_type() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.APPLICATION_XML)).andExpect(status().isOk());
    }

    @Test
    public void when_media_types_is_not_supported_responds_406() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.IMAGE_GIF)).andExpect(status().isNotAcceptable());
        rest.perform(get("/adverts").accept(MediaType.TEXT_HTML)).andExpect(status().isNotAcceptable());
        rest.perform(get("/adverts").accept(MediaType.TEXT_PLAIN)).andExpect(status().isNotAcceptable());
        rest.perform(get("/adverts").accept(MediaType.IMAGE_JPEG)).andExpect(status().isNotAcceptable());
        rest.perform(get("/adverts").accept(MediaType.IMAGE_PNG)).andExpect(status().isNotAcceptable());
        rest.perform(get("/adverts").accept(MediaType.APPLICATION_OCTET_STREAM)).andExpect(status().isNotAcceptable());
        rest.perform(get("/adverts").accept(MediaType.MULTIPART_FORM_DATA)).andExpect(status().isNotAcceptable());
    }

}