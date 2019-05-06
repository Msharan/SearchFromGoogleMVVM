package learn.example.com.zeta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import learn.example.com.zeta.Constants;
import learn.example.com.zeta.DetailActivity;
import learn.example.com.zeta.R;
import learn.example.com.zeta.model.Items;
import learn.example.com.zeta.model.SearchResultModel;

/**
 * Created by manisha.sharan on 04/05/19.
 */
public class SearchResultAdapter  extends RecyclerView.Adapter<SearchResultAdapter.Viewmodel>{
    private Context context;
    private SearchResultModel mSearchResult;
    private List<Items> mSearchResultItems;

    public SearchResultAdapter(Context context) {
        this.context = context;
    }

    public void setSearchResult(SearchResultModel searchResult) {
        this.mSearchResult = searchResult;
        mSearchResultItems = searchResult.getItems();
    }

    @NonNull
    @Override
    public Viewmodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewmodel(LayoutInflater.from(context).inflate(R.layout.item_search_result,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewmodel viewmodel, int position) {
        if(position < mSearchResultItems.size()) {
            viewmodel.title.setText(mSearchResultItems.get(position).getTitle());
            if(mSearchResultItems.get(position) != null
                    && mSearchResultItems.get(position).getPagemap() != null
                    && mSearchResultItems.get(position).getPagemap().getCse_thumbnail() != null
                    && mSearchResultItems.get(position).getPagemap().getCse_thumbnail().size() >0 )
                Glide.with(context).load(mSearchResultItems.get(position).getPagemap().getCse_thumbnail().get(0).getSrc()).centerCrop().into(viewmodel.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if(mSearchResultItems!=null )
            return mSearchResultItems.size();
        return 0;
    }

    class Viewmodel extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView description,title;

        public Viewmodel(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int POSITION = getAdapterPosition();
                    Intent i = new Intent(context,DetailActivity.class);
                    i.putExtra(Constants.IMAGE_URL,mSearchResultItems.get(POSITION).getPagemap().getCse_image().get(0).getSrc());
                    i.putExtra(Constants.TITLE,mSearchResultItems.get(POSITION).getTitle());
                    i.putExtra(Constants.SUBTITLE,mSearchResultItems.get(POSITION).getSnippet());
                    context.startActivity(i);

                }
            });
        }
    }
}
