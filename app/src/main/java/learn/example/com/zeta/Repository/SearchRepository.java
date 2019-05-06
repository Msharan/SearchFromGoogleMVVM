package learn.example.com.zeta.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.Map;

import learn.example.com.zeta.model.SearchResultModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manisha.sharan on 04/05/19.
 */
public class SearchRepository {
    private static Retrofit retrofit;
    private NetworkRepository mNetworkRepository;
    private static SearchRepository mInstance;
    private static final String BASE_URL = "https://www.googleapis.com";

    public SearchRepository() {
        getRetrofitInstance();
        mNetworkRepository = retrofit.create(NetworkRepository.class);
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static SearchRepository getInstance() {
        if(mInstance == null) {
            synchronized (SearchRepository.class) {
                if(mInstance == null) {
                    mInstance = new SearchRepository();
                }
            }
        }
        return mInstance;
    }

    public LiveData<SearchResultModel> getAllNewsFeed(Map<String,String> params) {
        final MutableLiveData<SearchResultModel> mutableLiveData = new MutableLiveData<>();
        mNetworkRepository.getAllSearchResult(params).enqueue(new Callback<SearchResultModel>() {
            @Override
            public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
                if(!response.isSuccessful()) {
                    Log.d("manisha","Error in response");
                } else
                    mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchResultModel> call, Throwable t) {
                Log.d("manisha","Failure in response");
            }
        });
        return mutableLiveData;
    }

}
