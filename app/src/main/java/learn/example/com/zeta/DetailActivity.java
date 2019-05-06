package learn.example.com.zeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView description,title;
    private String mImageUrl,mTitle,mSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getExtras();
        imageView = (ImageView)findViewById(R.id.image);
        description = findViewById(R.id.description);
        title = findViewById(R.id.title);
        setViews();
    }

    private void getExtras() {
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.IMAGE_URL)) {
            mImageUrl = intent.getStringExtra(Constants.IMAGE_URL);
        }
        if(intent.hasExtra(Constants.TITLE)) {
            mTitle = intent.getStringExtra(Constants.TITLE);
        }
        if(intent.hasExtra(Constants.SUBTITLE)) {
            mSubTitle = intent.getStringExtra(Constants.SUBTITLE);
        }
    }

    private void setViews() {
        if(!TextUtils.isEmpty(mTitle)) {
            title.setText(mTitle);
        }
        if(!TextUtils.isEmpty(mSubTitle)) {
            description.setText(mSubTitle);
        }
        if(!TextUtils.isEmpty(mImageUrl)) {
            Glide.with(DetailActivity.this).load(mImageUrl).centerCrop().into(imageView);
        }

    }
}
