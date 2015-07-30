package cn.com.mars.allen.phrclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.com.mars.allen.phrclient.R;

public class MycaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycase);

        LinearLayout l1=(LinearLayout)findViewById(R.id.history);
        LinearLayout l2=(LinearLayout)findViewById(R.id.writerec);
        Button bt=(Button)findViewById(R.id.mycases_back);


        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(MycaseActivity.this, PersonHealthHistoryActivity.class);
                startActivity(intent);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MycaseActivity.this, DiagnoseTableFillActivity.class);
                startActivity(intent);

            }
        });
    }

}
