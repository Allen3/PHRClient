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

import cn.com.mars.allen.phrclient.Beans.NewsInfo;
import cn.com.mars.allen.phrclient.Beans.PersonInfo;
import cn.com.mars.allen.phrclient.R;
import cn.com.mars.allen.phrclient.Util.Constants;
import cn.com.mars.allen.phrclient.Util.CustomHttpClient;

public class HandleNewsActivity extends AppCompatActivity {

    private AlertDialog loadingDialog;
    private ArrayList<NewsInfo> newsInfoList;
    private static final String SERVLET_TAG = "newsServlet";
    private String newsType = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_news);

        Intent intent = getIntent();
        newsType = intent.getStringExtra(Constants._NEWSTYPE_);

        // TODO
        // Obtain Database data.

        new ObtainNewsTask().execute(Constants.PATH + SERVLET_TAG);

        listView = (ListView)findViewById(R.id.messagelistview);
        Button bt=(Button)findViewById(R.id.message_list_back);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(HandleNewsActivity.this, NewsActivity.class);
                finish();

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(HandleNewsActivity.this, NewsContentActivity.class);

                NewsInfo newsInfo = (NewsInfo) parent.getItemAtPosition(position);
                intent.putExtra(Constants._NEWS_, new Gson().toJson(newsInfo));
                startActivity(intent);
            }
        });


        loadingDialog = new AlertDialog.Builder(HandleNewsActivity.this)
                .setTitle("Loading")
                .setMessage(R.string.loading_information)
                .show();

    }

    private ArrayList<NewsInfo> getData() {
        // TODO Auto-generated method stub
        return newsInfoList;
    }

    private class ObtainNewsTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;

            ArrayList<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair(Constants._NEWSTYPE_, newsType));
            result = CustomHttpClient.executeHttpGet(params[0], parameters);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                newsInfoList = new Gson().fromJson(result, new TypeToken<ArrayList<NewsInfo>>(){}.getType());

                loadingDialog.dismiss();
                listView.setAdapter(new ArrayAdapter<NewsInfo>(HandleNewsActivity.this, android.R.layout.simple_expandable_list_item_1, getData()));
            }
        }
    }
}
