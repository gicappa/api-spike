package jobengine.application;

import org.junit.Before;
import org.junit.Ignore;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:applicationContext.xml")
public class VersioningAdvertsTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc rest;

    @Before
    public void setup() {
        rest = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void when_no_media_type_is_specified_return_json() throws Exception {
        rest.perform(get("/adverts")).andExpect(content().contentType("application/json;charset=utf-8"));
    }
    @Test
    public void when_no_media_type_is_specified_return_version_v1() throws Exception {
        rest.perform(get("/adverts")).andExpect(content().contentType("application/json;charset=utf-8"));
        //assertThat(response.getHeaders().get("X-Jobrapido-Media-Type"), hasItem("v1; format=json"));
    }

    @Test
    public void when_json_accept_is_specified_return_json_last_version() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType("application/json;charset=utf-8"));
        //application/vnd.jobrapido.v1+json
    }

    @Test
    @Ignore
    public void when_alpha_json_accept_is_specified_return_json_alpha_version() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.parseMediaType("application/vnd.jobrapido.alpha+json"))).andExpect(content().contentType("application/json;charset=utf-8"));
        //application/vnd.jobrapido.v1+json
    }

    @Test
    @Ignore
    public void when_v1_json_accept_is_specified_return_json_v1_version() throws Exception {
        rest.perform(get("/adverts").accept(MediaType.parseMediaType("application/vnd.jobrapido.v1+json"))).andExpect(content().contentType("application/json;charset=utf-8"));
        //application/vnd.jobrapido.v1+json
    }
}