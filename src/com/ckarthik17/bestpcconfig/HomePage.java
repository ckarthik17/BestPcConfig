package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class HomePage extends Activity
{
    public static final String BLOGGER_API_KEY_PROPERTY = "BLOGGER_API_KEY";
    public static String BLOGGER_API_KEY;
    private final String LOG_TAG = "BEST PC CONFIG : ";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            Resources resources = this.getResources();
            AssetManager assetManager = resources.getAssets();

            InputStream inputStream = assetManager.open("config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            BLOGGER_API_KEY = (String) properties.get(BLOGGER_API_KEY_PROPERTY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(LOG_TAG, "Making HTTP call...");
        RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
        AsyncTask<Void, Void, String> executionResult = retrieveBlogContent.execute();
        try {
            Log.i(LOG_TAG, executionResult.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class RetrieveBlogContent extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("https://www.googleapis.com/blogger/v3/blogs/230842735819274721/posts/1186418786118781314?key=" + BLOGGER_API_KEY);
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = EntityUtils.toString(entity);
                Log.i(LOG_TAG, text);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return text;
        }
    }
}
