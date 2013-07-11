package search;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/search")
@Api(value = "/search", description = "Search operations")
public class SearchAPI {
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SearchResult DoSearch(@ApiParam(value = "The query", required = true) @QueryParam("q") String query) {
        SearchResult searchResult = new SearchResult();
        searchResult.setTitle(query);
        List<Advert> adverts = new ArrayList<Advert>();
        for (int i = 0; i <= 5; i++) {
            Advert advert = new Advert();
            advert.setUrl("antani_" + i);
            adverts.add(advert);
        }
        searchResult.setAdverts(adverts);

        return searchResult;
    }
}


