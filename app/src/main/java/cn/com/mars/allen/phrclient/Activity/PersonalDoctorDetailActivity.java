package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import cn.com.mars.allen.phrclient.Beans.Doctor;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;

public class PersonalDoctorDetailActivity extends AppCompatActivity {
    private TextView doctorName;
    private TextView doctorPhone;
    private TextView doctorProfile;
    private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_doctor_detail);

        doctorName = (TextView) findViewById(R.id.doctor_name);
        doctorPhone = (TextView) findViewById(R.id.doctor_phone);
        doctorProfile = (TextView) findViewById(R.id.doctor_intro);
        button_back = (Button) findViewById(R.id.doctorintroduction_back);

        Intent intent = getIntent();
        Doctor doctor = new Gson().fromJson(intent.getStringExtra(Constants.DOCTOR), Doctor.class);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        doctorName.setText(doctor.getDoc_name());
        doctorPhone.setText(doctor.getDoc_phone());
        doctorProfile.setText(doctor.getDoc_profile());
    }
}