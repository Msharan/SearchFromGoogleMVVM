package learn.example.com.zeta.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import learn.example.com.zeta.Constants;
import learn.example.com.zeta.Repository.SearchRepository;
import learn.example.com.zeta.model.SearchResultModel;

/**
 * Created by manisha.sharan on 04/05/19.
 */
public class SearchViewModel extends AndroidViewModel {
    private LiveData<SearchResultModel> searchResultLiveData;
    private SearchRepository searchRepository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        searchRepository = SearchRepository.getInstance();
    }

    public LiveData<SearchResultModel> getAllSearchResult(String query) {
        searchResultLiveData = searchRepository.getAllNewsFeed(getParams(query));
        return searchResultLiveData;
    }

    private Map<String,String> getParams(String query) {
        Map<String,String> params = new HashMap<>();
        params.put(Constants.API_KEY,Constants.API_KEY_VALUE);
        params.put(Constants.QUERY_PARAMETER,query);
        params.put(Constants.CX,Constants.CX_VALUE);
        return params;
    }
}
