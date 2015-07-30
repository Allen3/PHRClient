package cn.com.mars.allen.phrclient.Activity;

import android.app.Activity;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class LoginActivity extends AppCompatActivity {
    private final String SERVLET_TAG = "loginServlet";

    private AlertDialog loadingDialog;

    private EditText editText_ID;
    private EditText editText_password;
    private Button button_confirm;
    private Button button_register;
    private Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_ID = (EditText) findViewById(R.id.id_field);
        editText_password = (EditText) findViewById(R.id.password_field);
        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_register = (Button) findViewById(R.id.button_register);
        button_cancel = (Button) findViewById(R.id.button_cancel);

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idField = editText_ID.getText().toString();
                String passwordField = editText_password.getText().toString();

                new LoginTask().execute(Constants.PATH + SERVLET_TAG, idField, passwordField);

                loadingDialog = new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Loading")
                        .setMessage(R.string.loading_information)
                        .show();
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class LoginTask extends AsyncTask<String, String, Boolean> {


        @Override
        protected Boolean doInBackground(String... params) {
            String result = null;

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants.NAME, params[1]));
            parameters.add(new BasicNameValuePair(Constants.PASSWORD, params[2]));
            result = CustomHttpClient.executeHttpGet(params[0], parameters);

            if (result != null) {
                final PersonInfo personInfo = new Gson().fromJson(result, PersonInfo.class);
                if (!personInfo.getPerson_id().equals(Constants._LOGIN_FAIL_)) {

                    try {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(Constants.LOC_FILE_PERSONINFO, MODE_PRIVATE));
                        outputStreamWriter.write(result);
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
        protected void onPostExecute(Boolean isLoginFail) {
            super.onPostExecute(isLoginFail);

            loadingDialog.dismiss();
            if (isLoginFail) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Oops")
                        .setMessage(R.string.login_fail_message)
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {

                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Success")
                        .setMessage(R.string.login_success_message)
                        .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                Intent resultIntent = new Intent(LoginActivity.this, MainActivity.class);
                                setResult(Activity.RESULT_OK, resultIntent);

                                finish();
                            }
                        }).show();
            }

        }
    }
}
