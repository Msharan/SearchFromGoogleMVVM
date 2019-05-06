package learn.example.com.zeta.Repository;

import java.util.Map;

import learn.example.com.zeta.model.SearchResultModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by manisha.sharan on 04/05/19.
 */
public interface NetworkRepository {

    @GET("/customsearch/v1")
    Call<SearchResultModel> getAllSearchResult(@QueryMap Map<String,String> queryMap);
}
