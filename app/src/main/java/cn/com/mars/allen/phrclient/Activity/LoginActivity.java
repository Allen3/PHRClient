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

import java.util.ArrayList;

import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;
import cn.com.mars.allen.phrclient.Util.LOC_DatabaseHandler;

public class LoginActivity extends AppCompatActivity {
    private final String SERVLET_TAG = "loginServlet";

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

    private class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = null;

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants.NAME, params[1]));
            parameters.add(new BasicNameValuePair(Constants.PASSWORD, params[2]));
            result = CustomHttpClient.executeHttpGet(params[0], parameters);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {

                PersonInfo personInfo = new Gson().fromJson(result, PersonInfo.class);
                if (personInfo.getPerson_id().equals(Constants._LOGIN_FAIL_)) {

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
                    LOC_DatabaseHandler loc_databaseHandler = new LOC_DatabaseHandler(LoginActivity.this);

                    // Write into local database.
                    loc_databaseHandler.insertPersonInfo(personInfo);

                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Success")
                            .setMessage(R.string.login_success_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                    Intent resultIntent = new Intent(LoginActivity.this, MainActivity.class);

                    /**
                     * First content for log, second for vip.
                     */
                    boolean[] isLogAndVip = new boolean[2];
                    isLogAndVip[0] = true;;
                    isLogAndVip[1] = (personInfo.getVip() == 1);
                    resultIntent.putExtra("isLogAndVip", isLogAndVip);
                    setResult(Activity.RESULT_OK, resultIntent);

                    finish();
                }
            }

        }
    }
}
