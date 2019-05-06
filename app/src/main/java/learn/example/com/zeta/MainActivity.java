package learn.example.com.zeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mQueryEditText;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueryEditText = (EditText)findViewById(R.id.query_text);
        mSubmit = (Button)findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryString = mQueryEditText.getText().toString();
                if(!TextUtils.isEmpty(queryString)) {
                    Intent i = new Intent(MainActivity.this, SearchResultActivity.class);
                    i.putExtra(Constants.QUERY_STRING,queryString);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this,"Please provide valid query",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
