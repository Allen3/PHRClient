package cn.com.mars.allen.phrclient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.mars.allen.phrclient.R;

public class MainActivity extends AppCompatActivity {

    private static final int LOGINACTIVITY = 1;


    private boolean isLog = false;
    private boolean isVip = false;


    private TextView mineTextView;
    private RelativeLayout newsModule;
    private RelativeLayout vipModule;
    private RelativeLayout mineModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsModule = (RelativeLayout)findViewById(R.id.messageback);
        vipModule = (RelativeLayout) findViewById(R.id.moremainback);
        mineModule = (RelativeLayout) findViewById(R.id.mineback);
        mineTextView = (TextView) findViewById(R.id.main_minetext);
        if (!isLog) {
            mineTextView.setText(R.string.login);
        } else {
            mineTextView.setText(R.string.mine);
        }
        if (!isVip) {
            mineModule.setClickable(false);
        } else {
            mineModule.setClickable(true);
        }


        newsModule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        mineModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLog) {
                    // TODO
                    // List personal information.

                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, LOGINACTIVITY);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGINACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                boolean[] result = data.getBooleanArrayExtra("isLogAndVip");
                isLog = result[0];
                isVip = result[1];
            }
        }
    }
}
