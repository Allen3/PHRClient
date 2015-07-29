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
import android.widget.RadioButton;


import com.google.gson.Gson;

import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class RegisterActivity extends AppCompatActivity {
    private final String SERVLET_TAG = "registerServlet";

    private EditText editText_person_id;
    private EditText editText_password;
    private EditText editText_name;
    private RadioButton radioButton_gender_male;
    private RadioButton radioButton_gender_female;
    private EditText editText_age;
    private EditText editText_phone;
    private RadioButton radioButton_vip_true;
    private RadioButton radioButton_vip_false;
    private RadioButton[] radioButton_bloodtype = new RadioButton[4];
    /**
     * Not used.
     */
    private EditText editText_group_id;

    private Button button_confirm;
    private Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // TODO
        // Obtain the reference for these UI components.
        editText_person_id = (EditText) findViewById(R.id.register_person_id);
        editText_password = (EditText) findViewById(R.id.register_password);
        editText_name = (EditText) findViewById(R.id.register_name);
        radioButton_gender_male = (RadioButton) findViewById(R.id.register_gender_male);
        radioButton_gender_female = (RadioButton) findViewById(R.id.register_gender_female);
        editText_age = (EditText) findViewById(R.id.register_age);
        editText_phone = (EditText) findViewById(R.id.register_phone);
        radioButton_vip_true = (RadioButton) findViewById(R.id.register_vip_true);
        radioButton_vip_false = (RadioButton) findViewById(R.id.register_vip_false);
        radioButton_bloodtype[0] = (RadioButton) findViewById(R.id.register_bloodtype_A);
        radioButton_bloodtype[1] = (RadioButton) findViewById(R.id.register_bloodtype_B);
        radioButton_bloodtype[2] = (RadioButton) findViewById(R.id.register_bloodtype_AB);
        radioButton_bloodtype[3] = (RadioButton) findViewById(R.id.register_bloodtype_O);
        editText_group_id = (EditText) findViewById(R.id.register_groupid);


        button_cancel = (Button) findViewById(R.id.register_cancel);
        button_confirm = (Button) findViewById(R.id.register_confirm);


        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkRadioButtonSelection()) {
                    int gender = radioButton_gender_male.isActivated() ? 0 : 1;
                    int vip = radioButton_vip_false.isActivated() ? 0 : 1;
                    String bloodType = null;
                    if (radioButton_bloodtype[0].isActivated()) {
                        bloodType = "A";
                    } else if (radioButton_bloodtype[1].isActivated()) {
                        bloodType = "B";
                    } else if (radioButton_bloodtype[2].isActivated()) {
                        bloodType = "AB";
                    } else if (radioButton_bloodtype[3].isActivated()) {
                        bloodType = "O";
                    }

                    PersonInfo personInfo = new PersonInfo(
                            editText_person_id.toString(),
                            editText_password.toString(),
                            editText_name.toString(),
                            gender,
                            Integer.valueOf(editText_age.toString()),
                            editText_phone.toString(),
                            vip,
                            bloodType,
                            Integer.valueOf(editText_group_id.toString())
                    );
                    String json = new Gson().toJson(personInfo);

                    new RegisterTask().execute(Constants.PATH + SERVLET_TAG, json);
                } else {
                    return;
                }

            }
        });


    }

    private boolean checkRadioButtonSelection() {
        if (radioButton_gender_male.isActivated() && radioButton_gender_female.isActivated()) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Oops")
                    .setMessage(R.string.register_multigender_message)
                    .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        } else if (!radioButton_gender_male.isActivated() && !radioButton_gender_female.isActivated()) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Oops")
                    .setMessage(R.string.register_nogender_message)
                    .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }

        if (radioButton_vip_true.isActivated() && radioButton_vip_false.isActivated()) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Oops")
                    .setMessage(R.string.register_multivip_message)
                    .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        } else if (!radioButton_vip_true.isActivated() && !radioButton_vip_false.isActivated()) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Oops")
                    .setMessage(R.string.register_novip_message)
                    .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }


        int bloodTypeCnt = 0;
        for (RadioButton radioButton : radioButton_bloodtype) {
            if (radioButton.isActivated()) {
                bloodTypeCnt++;
                if (bloodTypeCnt > 1) {
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Oops")
                            .setMessage(R.string.register_multibloodtype_message)
                            .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                    return false;
                }
            }
        }
        if (bloodTypeCnt == 0) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Oops")
                    .setMessage(R.string.register_nobloodtype_message)
                    .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }
        return true;
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

