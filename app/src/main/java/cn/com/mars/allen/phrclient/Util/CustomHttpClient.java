package cn.com.mars.allen.phrclient.Util;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Allen on 2015/7/26.
 */
public class CustomHttpClient {
    public static final int HTTP_TIMEOUT= 30 * 1000;

    private static HttpClient httpClient;

    private static HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new DefaultHttpClient();

            //HttpProtocolParams.setUserAgent(httpClient.getParams(), "PHRClient/1.0");
            final HttpParams httpParams = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, HTTP_TIMEOUT);
            ConnManagerParams.setTimeout(httpParams, HTTP_TIMEOUT);
        }

        return httpClient;
    }


    public static String executeHttpPost(String url, String json) {
        String result = null;
        httpClient = getHttpClient();

        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(json));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
                StringBuilder builder = new StringBuilder();

                for (String line = null; (line = reader.readLine()) != null; ) {
                    builder.append(line).append(System.getProperty("line.separator"));
                }

                result = builder.toString();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String executeHttpGet(String url, ArrayList<NameValuePair> parameters) {
        String result = null;
        httpClient = getHttpClient();

        String Uri = url + "?";
        for (int i = 0;i < parameters.size();i ++) {
            Uri += parameters.get(i).getName() + "=" + parameters.get(i).getValue();
            if (i < parameters.size() - 1) {
                Uri += "&";
            }
        }

        HttpGet httpGet = new HttpGet(Uri);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
                StringBuilder builder = new StringBuilder();

                for (String line = null; (line = reader.readLine()) != null; ) {
                    builder.append(line).append(System.getProperty("line.separator"));
                }


                result = builder.toString();
            }

        } catch (IOException e) {
            Log.e("!!!!!!", "IOException");
            e.printStackTrace();
        }

        return result;
    }
}
