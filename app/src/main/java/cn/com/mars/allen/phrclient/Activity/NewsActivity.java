package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        LinearLayout btn1=(LinearLayout)findViewById(R.id.healthinformation);
        LinearLayout btn2=(LinearLayout)findViewById(R.id.druginformation);
        LinearLayout btn3=(LinearLayout)findViewById(R.id.slowmanagement);
        LinearLayout btn4=(LinearLayout)findViewById(R.id.immunization);
        LinearLayout btn5=(LinearLayout)findViewById(R.id.news);
        Button bt=(Button)findViewById(R.id.message_back);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(NewsActivity.this,HandleNewsActivity.class);
                intent.putExtra(Constants._NEWSTYPE_, Constants.HEALTH_NEWS);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(NewsActivity.this,HandleNewsActivity.class);
                intent.putExtra(Constants._NEWSTYPE_, Constants.DRUG_NEWS);
                startActivity(intent);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(NewsActivity.this,HandleNewsActivity.class);
                intent.putExtra(Constants._NEWSTYPE_, Constants.DISEASE_NEWS);
                startActivity(intent);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(NewsActivity.this,HandleNewsActivity.class);
                intent.putExtra(Constants._NEWSTYPE_, Constants.IMMUNE_NEWS);
                startActivity(intent);

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(NewsActivity.this,HandleNewsActivity.class);
                intent.putExtra(Constants._NEWSTYPE_, Constants.NEWS_NEWS);
                startActivity(intent);

            }
        });
    }
}
