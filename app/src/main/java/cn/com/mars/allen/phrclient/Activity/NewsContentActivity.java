package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import cn.com.mars.allen.phrclient.Beans.NewsInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;

public class NewsContentActivity extends AppCompatActivity {
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Button bt=(Button)findViewById(R.id.message_item_back);
        contentTextView = (TextView) findViewById(R.id.message_item_content);

        Intent intent = getIntent();
        String json = intent.getStringExtra(Constants._NEWS_);
        NewsInfo newsInfo = new Gson().fromJson(json, NewsInfo.class);

        contentTextView.setText(newsInfo.getNews_Content());

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewsContentActivity.this, HandleNewsActivity.class);
                finish();
            }
        });
    }
}
