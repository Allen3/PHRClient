package cn.com.mars.allen.phrclient.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;

import cn.com.mars.allen.phrclient.Beans.Hospital;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class BodyCheckActivity extends AppCompatActivity {
    private static final String SERVLET_TAG = "hospitalServlet";

    private ListView listView;
    private ArrayList<Hospital> hospitalList;
    private AlertDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_check);

        listView = (ListView) findViewById(R.id.bodycheck_hospitallstview);
        Button bt = (Button) findViewById(R.id.bodycheck_hospital_back);

        new ObtainHospitalTask().execute(Constants.PATH + SERVLET_TAG);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Hospital hospital = (Hospital) parent.getItemAtPosition(position);

                new AlertDialog.Builder(BodyCheckActivity.this)
                        .setTitle("Confirm")
                        .setMessage(R.string.whether_appointment_message)
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
                                // Appointment.

                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        loadingDialog = new AlertDialog.Builder(BodyCheckActivity.this)
                .setTitle("Loading")
                .setMessage(R.string.loading_information)
                .show();

    }

    private ArrayList<Hospital> getData() {
        return hospitalList;
    }

    private class ObtainHospitalTask extends AsyncTask<String, String, String> {
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
                hospitalList = new Gson().fromJson(result, new TypeToken<ArrayList<Hospital>>() {
                }.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<Hospital>(BodyCheckActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }

}
