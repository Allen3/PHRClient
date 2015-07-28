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

import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;
import cn.com.mars.allen.phrclient.Util.LoginResponse;
import cn.com.mars.allen.phrclient.Util.PersonInfo;
import cn.com.mars.allen.phrclient.Util.RegisterResponse;

public class LoginActivity extends AppCompatActivity {
    public static final int REGISTER_ACTIVITY_TOKEN = 1;
    private final String SERVLET_TAG = "loginRegisterServlet";

    private EditText editText_ID;
    private EditText editText_password;
    private Button button_confirm;
    private Button button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_ID = (EditText) findViewById(R.id.id_field);
        editText_password = (EditText) findViewById(R.id.password_field);
        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_register = (Button) findViewById(R.id.button_register);

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idField = editText_ID.getText().toString();
                String passwordField = editText_password.getText().toString();

                new LoginTask().execute(Constants.PATH + SERVLET_TAG, Constants._LOGIN_, idField, passwordField);
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTER_ACTIVITY_TOKEN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGISTER_ACTIVITY_TOKEN) {
            if (resultCode == Activity.RESULT_OK) {

                String json = data.getStringExtra("personInfoJSON");

                new RegisterTask().execute(Constants.PATH + SERVLET_TAG, Constants._REGISTER_, json);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = null;

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants._FLAG_, params[1]));
            parameters.add(new BasicNameValuePair(Constants.USERNAME, params[2]));
            parameters.add(new BasicNameValuePair(Constants.PASSWORD, params[3]));
            result = CustomHttpClient.executeHttpGet(params[0], parameters);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
//TEST
                Log.e("JLJKLJ", result);

                LoginResponse response = new Gson().fromJson(result, LoginResponse.class);
                if (response.get_LOGIN_RESPONSE().equals(Constants._LOGIN_SUCCESS_)) {
                    // TODO
                    // successfully login.
                    Log.e("Success............", "Blablablabla");

                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Oops")
                            .setMessage(R.string.login_fail_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }

        }
    }

    private class RegisterTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants._FLAG_, params[1]));
            result = CustomHttpClient.executeHttpPost(params[0], parameters, params[2]);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
//TEST
                Log.e("JLJKLJ", result);

                RegisterResponse response = new Gson().fromJson(result, RegisterResponse.class);
                if (response.get_REGISTER_RESPONSE().equals(Constants._REGISTER_SUCCESS_)) {

                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Success!")
                            .setMessage(R.string.register_success_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Oops")
                            .setMessage(R.string.register_fail_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        }
    }
}
