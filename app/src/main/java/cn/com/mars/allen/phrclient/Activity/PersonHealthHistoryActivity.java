package cn.com.mars.allen.phrclient.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.com.mars.allen.phrclient.Beans.Doctor;
import cn.com.mars.allen.phrclient.Beans.PersonHealth;
import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class PersonHealthHistoryActivity extends AppCompatActivity {
    private static final String SERVLET_TAG = "personHealthServlet";

    private ListView listView;
    private ArrayList<PersonHealth> personHealthList;
    private AlertDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_health_history);

        listView = (ListView)findViewById(R.id.doctorlistview);
        Button bt=(Button)findViewById(R.id.doctor_back);

        new ObtainPersonHealthTask().execute(Constants.PATH + SERVLET_TAG);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                /*
                Doctor doctor = (Doctor) parent.getItemAtPosition(position);

                String doctorJSON = new Gson().toJson(doctor);
                Intent intent = new Intent(PersonHealthHistoryActivity.this, PersonalDoctorDetailActivity.class);
                intent.putExtra(Constants.DOCTOR, doctorJSON);
                startActivity(intent);
                */

            }
        });

        loadingDialog = new AlertDialog.Builder(PersonHealthHistoryActivity.this)
                .setTitle("Loading")
                .setMessage(R.string.loading_information)
                .show();
    }

    private ArrayList<PersonHealth> getData() {
        return personHealthList;
    }

    private class ObtainPersonHealthTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String obtainPersonInforesult = null;
            String obtainPersonHealthListResult = null;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(Constants.LOC_FILE_PERSONINFO)));
                StringBuilder builder = new StringBuilder();

                for (String line = null; (line = reader.readLine()) != null; ) {
                    builder.append(line).append(System.getProperty("line.separator"));
                }

                obtainPersonInforesult = builder.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // TODO
            // Update the parameters.

            PersonInfo personInfo = new Gson().fromJson(obtainPersonInforesult, PersonInfo.class);

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants.PERSON_ID, personInfo.getPerson_id()));
            obtainPersonHealthListResult = CustomHttpClient.executeHttpGet(url, parameters);

            return obtainPersonHealthListResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                personHealthList = new Gson().fromJson(result, new TypeToken<ArrayList<PersonHealth>>(){}.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<PersonHealth>(PersonHealthHistoryActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }
}
