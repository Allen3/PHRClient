package cn.com.mars.allen.phrclient.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class DiagnoseDoctorActivity extends AppCompatActivity {
    private static final String SERVLET_TAG = "doctorServlet";

    private ListView listView;
    private ArrayList<Doctor> doctorList;
    private AlertDialog loadingDialog;
    private String dep_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_doctor);

        Intent intent = getIntent();
        dep_id = intent.getStringExtra(Constants.DEP_ID);

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
                // TODO
                // Diagnose...
                Doctor doctor = (Doctor) parent.getItemAtPosition(position);
                Log.e("blabla", doctor.getDoc_name());
                Log.e("blabla", doctor.getDoc_phone());

                new AlertDialog.Builder(DiagnoseDoctorActivity.this)
                        .setTitle("Confirm")
                        .setMessage(R.string.diagnose_confirm_message)
                        .setNegativeButton(R.string.diaglog_cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // TODO
                                // diagnose
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        loadingDialog = new AlertDialog.Builder(DiagnoseDoctorActivity.this)
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

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants.DEP_ID, dep_id));
            result = CustomHttpClient.executeHttpGet(params[0], parameters);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                doctorList = new Gson().fromJson(result, new TypeToken<ArrayList<Doctor>>(){}.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<Doctor>(DiagnoseDoctorActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }

}
