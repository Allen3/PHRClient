package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.com.mars.allen.phrclient.R;

public class VipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        Button bt=(Button)findViewById(R.id.vip_back);
        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.healthycheckback);
        RelativeLayout r2 = (RelativeLayout) findViewById(R.id.privatedoctorback);
        RelativeLayout r3 = (RelativeLayout) findViewById(R.id.healthindexback);
        RelativeLayout r4 = (RelativeLayout) findViewById(R.id.healthproback);
        RelativeLayout r5 = (RelativeLayout) findViewById(R.id.familygroupback);
        RelativeLayout r6 = (RelativeLayout) findViewById(R.id.mycasesback);
        RelativeLayout r7 = (RelativeLayout) findViewById(R.id.doctorremindback);
        RelativeLayout r8 = (RelativeLayout) findViewById(R.id.equipmentleaseback);
        RelativeLayout r9 = (RelativeLayout) findViewById(R.id.nocback);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        r1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, BodyCheckActivity.class);
                startActivity(intent);

            }
        });
        r2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, PersonalDoctorActivity.class);
                startActivity(intent);

            }
        });
        r3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, HealthIndexActivity.class);
                startActivity(intent);

            }
        });
        r4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, HealthImproveActivity.class);
                startActivity(intent);

            }
        });
        r5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, FamilygroupActivity.class);
                startActivity(intent);

            }
        });
        r6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, MycaseActivity.class);
                startActivity(intent);

            }
        });
        r7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, PromptActivity.class);
                startActivity(intent);

            }
        });
        r8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VipActivity.this, EquipmentActivity.class);
                startActivity(intent);

            }
        });
        r9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipActivity.this, OnlineConsultActivity.class);
                startActivity(intent);

            }
        });
    }

}
