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

import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;
import cn.com.mars.allen.phrclient.Util.PersonInfo;

public class RegisterActivity extends AppCompatActivity {
    private final String SERVLET_TAG = "registerServlet";

    private EditText editText_person_id;
    private EditText editText_password;
    private EditText editText_name;
    private EditText editText_gender;
    /**
     * Should be selections.
     */
    private EditText editText_age;
    private EditText editText_phone;
    /**
     * Should be selections.
     */
    private EditText editText_vip;
    /**
     * Should be selections.
     */
    private EditText editText_bloodType;
    /**
     * Not used.
     */
    private EditText editText_group_id;

    private Button button_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // TODO
        // Obtain the reference for these UI components.
        Intent intent = getIntent();

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonInfo personInfo = new PersonInfo(
                        editText_person_id.toString(),
                        editText_password.toString(),
                        editText_name.toString(),
                        Integer.valueOf(editText_gender.toString()),
                        Integer.valueOf(editText_age.toString()),
                        editText_phone.toString(),
                        Integer.valueOf(editText_vip.toString()),
                        editText_bloodType.toString(),
                        Integer.valueOf(editText_group_id.toString())
                        );
                String json = new Gson().toJson(personInfo);

                new RegisterTask().execute(Constants.PATH + SERVLET_TAG, json);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    private class RegisterTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;

            result = CustomHttpClient.executeHttpPost(params[0], params[1]);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
//TEST
                Log.e("JLJKLJ", result);

                String response = new Gson().fromJson(result, String.class);
                if (response.equals(Constants._REGISTER_SUCCESS_)) {

                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Success!")
                            .setMessage(R.string.register_success_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                } else {
                    new AlertDialog.Builder(RegisterActivity.this)
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

