package jobengine.api;

public interface Adverts {
    Result search(String what, String where, Integer nSponsored, Integer nPage, Integer nResultPerPage);
    Result viewAdvert(Long id);
    Result saveAdvert(Long id);
    Result removeAdvert(Long id);
}
