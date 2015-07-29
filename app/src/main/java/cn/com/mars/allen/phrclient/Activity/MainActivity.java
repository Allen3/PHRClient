package cn.com.mars.allen.phrclient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;

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

        newsModule = (RelativeLayout) findViewById(R.id.messageback);
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

                isLog = true;
                mineTextView.setText(R.string.mine);
                //  TODO
                // get data from personal info file.

                new ReadFileTask().execute();

            }
        }
    }


    private class ReadFileTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(Constants.LOC_FILE_PERSONINFO)));
                StringBuilder builder = new StringBuilder();

                for (String line = null; (line = reader.readLine()) != null; ) {
                    builder.append(line).append(System.getProperty("line.separator"));
                }

                result = builder.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            
            final PersonInfo personInfo = new Gson().fromJson(result, PersonInfo.class);

            if (personInfo.getVip() == 1) {
                isVip = true;
                mineModule.setClickable(true);
            }
        }
    }
}
