package cn.com.mars.allen.phrclient.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import cn.com.mars.allen.phrclient.Beans.Department;
import cn.com.mars.allen.phrclient.Beans.Hospital;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class DiagnoseDepartmentActivity extends AppCompatActivity {
    private static final String SERVLET_TAG = "departmentServlet";

    private ListView listView;
    private ArrayList<Department> departmentList;
    private AlertDialog loadingDialog;
    private String hid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_department);

        Intent intent = getIntent();
        hid = intent.getStringExtra(Constants.HID);

        listView=(ListView)findViewById(R.id.departmentlistview);
        Button bt=(Button)findViewById(R.id.department_back);

        new ObtainDepartmentTask().execute(Constants.PATH + SERVLET_TAG);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(DiagnoseDepartmentActivity.this, DiagnoseDoctorActivity.class);

                Department department = (Department) parent.getItemAtPosition(position);
                intent.putExtra(Constants.DEP_ID, department.getDep_id().toString());

                startActivity(intent);
            }
        });

        loadingDialog = new AlertDialog.Builder(DiagnoseDepartmentActivity.this)
                .setTitle("Loading")
                .setMessage(R.string.loading_information)
                .show();
    }

    private ArrayList<Department> getData() {
        return departmentList;
    }

    private class ObtainDepartmentTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants.HID, hid));
            result = CustomHttpClient.executeHttpGet(params[0], parameters);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                departmentList = new Gson().fromJson(result, new TypeToken<ArrayList<Department>>(){}.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<Department>(DiagnoseDepartmentActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }
}
