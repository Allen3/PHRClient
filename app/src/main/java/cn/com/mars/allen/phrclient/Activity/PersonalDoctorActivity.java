package cn.com.mars.allen.phrclient.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

import cn.com.mars.allen.phrclient.Beans.Doctor;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class PersonalDoctorActivity extends AppCompatActivity {

    private static final String SERVLET_TAG = "doctorServlet";

    private ListView listView;
    private ArrayList<Doctor> doctorList;
    private AlertDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_doctor);

        listView=(ListView)findViewById(R.id.doctorlistview);
        Button bt=(Button)findViewById(R.id.doctor_back);

        new ObtainDoctorTask().execute(Constants.PATH + SERVLET_TAG);

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
                Doctor doctor = (Doctor) parent.getItemAtPosition(position);

                String doctorJSON = new Gson().toJson(doctor);
                Intent intent = new Intent(PersonalDoctorActivity.this, PersonalDoctorDetailActivity.class);
                intent.putExtra(Constants.DOCTOR, doctorJSON);
                startActivity(intent);

            }
        });

        loadingDialog = new AlertDialog.Builder(PersonalDoctorActivity.this)
                .setTitle("Loading")
                .setMessage(R.string.loading_information)
                .show();
    }

    private ArrayList<Doctor> getData() {
        return doctorList;
    }

    private class ObtainDoctorTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;

            result = CustomHttpClient.executeHttpGet(params[0], null);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                doctorList = new Gson().fromJson(result, new TypeToken<ArrayList<Doctor>>(){}.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<Doctor>(PersonalDoctorActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }
}
