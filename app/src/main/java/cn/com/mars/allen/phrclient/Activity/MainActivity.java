package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import cn.com.mars.allen.phrclient.R;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout newsModule;
    private RelativeLayout mineModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsModule = (RelativeLayout)findViewById(R.id.messageback);
        mineModule = (RelativeLayout) findViewById(R.id.mineback);

        newsModule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);

            }
        });

        mineModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
