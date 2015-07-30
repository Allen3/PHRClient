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
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cn.com.mars.allen.phrclient.Beans.Diagnose;
import cn.com.mars.allen.phrclient.Beans.PersonHealth;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class DiagnoseTableFillActivity extends AppCompatActivity {
    private final String SERVLET_TAG = "personHealthServlet";

    private Diagnose diagnose = null;

    private EditText drugName;
    private EditText drugDose;
    private Button button_submit;
    private Button button_back;
    private AlertDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_table_fill);

        drugName = (EditText) findViewById(R.id.drug_name);
        drugDose = (EditText) findViewById(R.id.drug_dose);
        button_submit = (Button) findViewById(R.id.drug_up);
        button_back = (Button) findViewById(R.id.personhealth_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonHealth personHealth = new PersonHealth();

                // obtain diagnose object from file.
                new ObtainDiagnoseFromfile().execute();

                personHealth.setPerson_id(diagnose.getPerson_id());
                personHealth.setDiag_date(diagnose.getDiagnose_date());
                personHealth.setDrug_name(drugName.getText().toString());
                personHealth.setDrug_dose(drugDose.getText().toString());

                new SubmitPersonHealth().execute(Constants.PATH + SERVLET_TAG, new Gson().toJson(personHealth));

                loadingDialog = new AlertDialog.Builder(DiagnoseTableFillActivity.this)
                        .setTitle("Loading")
                        .setMessage(R.string.loading_information)
                        .show();
            }
        });

    }

    private class SubmitPersonHealth extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String url = params[0];
            String json = params[1];
            String objectTransferResult = null;

            objectTransferResult = CustomHttpClient.executeHttpPost(url, json);

            if (objectTransferResult != null) {
                if ((new Gson().fromJson(objectTransferResult, String.class)).equals(Constants._SUBMIT_PERSONHEALTH_SUCCESS_)) {

                    try {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(Constants.LOC_FILE_PERSONHEALTH, MODE_PRIVATE));
                        outputStreamWriter.write(objectTransferResult);
                        outputStreamWriter.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return false;

                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean isSubmitPersonHealthFail) {
            super.onPostExecute(isSubmitPersonHealthFail);

            loadingDialog.dismiss();
            if (isSubmitPersonHealthFail) {
                new AlertDialog.Builder(DiagnoseTableFillActivity.this)
                        .setTitle("Oops")
                        .setMessage(R.string.submit_personhealth_fail_message)
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                new AlertDialog.Builder(DiagnoseTableFillActivity.this)
                        .setTitle("Success!")
                        .setMessage(R.string.submit_personhealth_success_message)
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        }
    }

    private class ObtainDiagnoseFromfile extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String result = null;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(Constants.LOC_FILE_DIAGNOSE)));
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
                diagnose = new Gson().fromJson(result, Diagnose.class);
            }
        }
    }
}
