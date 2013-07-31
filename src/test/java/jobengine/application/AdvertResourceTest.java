package jobengine.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:applicationContext.xml")
public class AdvertResourceTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc rest;

    @Before
    public void setup() {
        rest = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void when_resource_not_existing_it_responds_404() throws Exception {
        rest.perform(get("/not-existing-url")).andExpect(status().isNotFound());
    }

    @Test
    public void when_http_verb_is_not_implemented_responds_405() throws Exception {
        rest.perform(put("/adverts")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void when_requesting_adverts_it_responds_200() throws Exception {
        rest.perform(get("/adverts")).andExpect(status().isOk());
    }

    @Test
    public void when_requesting_adverts_with_params_it_responds_200() throws Exception {
        rest.perform(get("/adverts?page=0&size=5")).andExpect(status().isOk());
    }

    @Test
    public void when_a_single_advert_is_requested_it_responds_200() throws Exception {
        rest.perform(get("/adverts/4")).andExpect(status().isOk());
    }

    @Test
    public void when_advert_id_does_not_exist_it_responds_404() throws Exception {
        rest.perform(get("/adverts/1000")).andExpect(status().isNotFound());
    }

}
