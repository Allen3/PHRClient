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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cn.com.mars.allen.phrclient.Beans.Diagnose;
import cn.com.mars.allen.phrclient.Beans.Doctor;
import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class DiagnoseDoctorActivity extends AppCompatActivity {
    private static final String SERVLET_TAG = "doctorServlet";

    private Diagnose diagnose = null;

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
                final Doctor doctor = (Doctor) parent.getItemAtPosition(position);

                new AlertDialog.Builder(DiagnoseDoctorActivity.this)
                        .setTitle("Confirm")
                        .setMessage(R.string.diagnose_confirm_message)
                        .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {

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
                                new DiagnoseTask().execute(Constants.PATH + SERVLET_TAG, doctor.getDoctor_id().toString());
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


    private class DiagnoseTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String objectTransferResult = null;
            String fileReadingResult = null;
            String url = params[0];
            String doctor_id = params[1];

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(Constants.LOC_FILE_PERSONINFO)));
                StringBuilder builder = new StringBuilder();

                for (String line = null; (line = reader.readLine()) != null; ) {
                    builder.append(line).append(System.getProperty("line.separator"));
                }

                fileReadingResult = builder.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (fileReadingResult != null) {
                final PersonInfo personInfo = new Gson().fromJson(fileReadingResult, PersonInfo.class);

                diagnose = new Diagnose();
                diagnose.setPerson_id(personInfo.getPerson_id());
                diagnose.setDoctor_id(Integer.parseInt(doctor_id));
                diagnose.setDiagnose_date(new SimpleDateFormat("EEE, d MMM yyyy").format(Calendar.getInstance().getTime()));

                objectTransferResult = CustomHttpClient.executeHttpPost(url, new Gson().toJson(diagnose));
            }

            return objectTransferResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {

                String response = new Gson().fromJson(result, String.class);
                if (response.equals(Constants._DIAGNOSE_SUCCESS_)) {

                    new AlertDialog.Builder(DiagnoseDoctorActivity.this)
                            .setTitle("Success!")
                            .setMessage(R.string.diagnose_success_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    new Write2LocalDiagnoseInfo().execute(new Gson().toJson(diagnose));

                                    dialog.dismiss();
                                }
                            }).show();

                } else {
                    new AlertDialog.Builder(DiagnoseDoctorActivity.this)
                            .setTitle("Oops")
                            .setMessage(R.string.diagnose_fail_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        }

        private class Write2LocalDiagnoseInfo extends AsyncTask<String, String, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(Constants.LOC_FILE_DIAGNOSE, MODE_PRIVATE));
                    outputStreamWriter.write(params[0]);
                    outputStreamWriter.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
    }
}
