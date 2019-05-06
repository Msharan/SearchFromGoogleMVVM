package learn.example.com.zeta;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import learn.example.com.zeta.adapter.SearchResultAdapter;
import learn.example.com.zeta.model.SearchResultModel;
import learn.example.com.zeta.viewmodel.SearchViewModel;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SearchViewModel mSearchViewModel;
    private SearchResultAdapter mSearchResultAdapter;
    private SearchResultModel mSearchResult;
    private String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        if(getIntent().hasExtra(Constants.QUERY_STRING)) {
            query = getIntent().getStringExtra(Constants.QUERY_STRING);
        }
        mRecyclerView = (RecyclerView)findViewById(R.id.search_result_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultAdapter = new SearchResultAdapter(SearchResultActivity.this);
        mRecyclerView.setAdapter(mSearchResultAdapter);
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        ((SearchViewModel) mSearchViewModel).getAllSearchResult(query).observe(SearchResultActivity.this, new Observer<SearchResultModel>() {
            @Override
            public void onChanged(@Nullable SearchResultModel searchResult) {
                mSearchResult = searchResult;
                mSearchResultAdapter.setSearchResult(mSearchResult);
                mRecyclerView.setAdapter(mSearchResultAdapter);
                Log.d("manisha",String.valueOf(mSearchResult.getItems().size()));
            }
        });
    }
}
