package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cn.com.mars.allen.phrclient.R;

public class FamilygroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familygroup);

        Button bt1=(Button)findViewById(R.id.family_back);
        Button bt2=(Button)findViewById(R.id.family_add);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FamilygroupActivity.this, FamilyaddActivity.class);
                startActivity(intent);
            }
        });
    }

}
