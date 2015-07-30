package cn.com.mars.allen.phrclient.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private RelativeLayout diagnoseModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diagnoseModule = (RelativeLayout) findViewById(R.id.diagnoseback);
        newsModule = (RelativeLayout) findViewById(R.id.messageback);
        vipModule = (RelativeLayout) findViewById(R.id.vipmainback);
        mineModule = (RelativeLayout) findViewById(R.id.mineback);
        mineTextView = (TextView) findViewById(R.id.main_minetext);
        if (!isLog) {
            mineTextView.setText(R.string.login);
            diagnoseModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Oops")
                            .setMessage(R.string.not_login_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });

        } else {
            mineTextView.setText(R.string.mine);
            diagnoseModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DiagnoseHospitalActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (!isVip) {

            vipModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Oops")
                            .setMessage(R.string.not_vip_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });
        } else {
            mineModule.setClickable(true);
            vipModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, VipActivity.class);
                    startActivity(intent);
                }
            });
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
                    Intent intent = new Intent(MainActivity.this, DisplayPersonInfo.class);
                    startActivity(intent);

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
                mineTextView.setText(R.string.mine);
                diagnoseModule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DiagnoseHospitalActivity.class);
                        startActivity(intent);
                    }
                });

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

            if (result != null) {
                final PersonInfo personInfo = new Gson().fromJson(result, PersonInfo.class);

                if (personInfo.getVip() == 1) {
                    isVip = true;
                    vipModule.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, VipActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }
}
