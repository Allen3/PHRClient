package cn.com.mars.allen.phrclient.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;

public class DisplayPersonInfo extends AppCompatActivity {
    PersonInfo personInfo;

    private TextView personalname;
    private TextView personalsex;
    private TextView personalblood;
    private TextView personalid;
    private TextView personalphone;
    private TextView personalage;
    private TextView personalfamilyid;
    private TextView personalvip;

    private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person_info);

        personalname = (TextView) findViewById(R.id.personalname);
        personalsex = (TextView) findViewById(R.id.personalsex);
        personalblood = (TextView) findViewById(R.id.personalblood);
        personalid = (TextView) findViewById(R.id.personalid);
        personalphone = (TextView) findViewById(R.id.personalphone);
        personalage = (TextView) findViewById(R.id.personalage);
        personalfamilyid = (TextView) findViewById(R.id.personalfamilyid);
        personalvip = (TextView) findViewById(R.id.personalvip);
        button_back = (Button) findViewById(R.id.personaldata_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new ObtainPersonInfoFromFile().execute();
    }

    private class ObtainPersonInfoFromFile extends AsyncTask<String, String, String> {

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

                personalname.setText(personInfo.getName());
                if (personInfo.getGender() == 0) {
                    personalsex.setText("女");
                } else {
                    personalsex.setText("男");
                }
                personalblood.setText(personInfo.getBloodType());
                personalid.setText(personInfo.getPerson_id());
                personalphone.setText(personInfo.getPhone());
                personalage.setText(personInfo.getAge().toString());
                personalfamilyid.setText(personInfo.getGroup_id().toString());
                if (personInfo.getVip() == 1) {
                    personalvip.setText("是");
                } else {
                    personalvip.setText("否");
                }
            }
        }

    }
}
