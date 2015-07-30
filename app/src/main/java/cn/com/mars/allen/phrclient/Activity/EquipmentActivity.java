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

import cn.com.mars.allen.phrclient.Beans.Equipment;
import cn.com.mars.allen.phrclient.Beans.NewsInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class EquipmentActivity extends AppCompatActivity {
    private static final String SERVLET_TAG = "equipmentServlet";

    private ListView listView;
    private AlertDialog loadingDialog;
    private ArrayList<Equipment> equipmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        listView = (ListView)findViewById(R.id.equipmentlistview);
        Button bt=(Button)findViewById(R.id.equipment_back);

        new ObtainEquipmentTask().execute(Constants.PATH + SERVLET_TAG);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int postion,
                                    long id) {

                // TODO
                // Display the equipment detail information.

            }
        });
        loadingDialog = new AlertDialog.Builder(EquipmentActivity.this)
                .setTitle("Loading")
                .setMessage(R.string.loading_information)
                .show();
    }

    private ArrayList<Equipment> getData() {
        return equipmentList;
    }

    private class ObtainEquipmentTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;

            result = CustomHttpClient.executeHttpGet(params[0], null);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                equipmentList = new Gson().fromJson(result, new TypeToken<ArrayList<Equipment>>(){}.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<Equipment>(EquipmentActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }

}
