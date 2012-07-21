package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UltraConfigActivity extends Activity
{
    public static final String BLOGGER_API_KEY_PROPERTY = "BLOGGER_API_KEY";
    public static String BLOGGER_API_KEY;
    private final String LOG_TAG = "BEST PC CONFIG : ";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ultra_config);

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
            String responseString = executionResult.get();
            Log.i(LOG_TAG, responseString);

            JSONObject json = new JSONObject(responseString);
            String htmlContent = (String) json.get("content");

            Document blogContentDocument = Jsoup.parse(htmlContent);
            Elements intelTable = blogContentDocument.select("#configTable");

            TableLayout uiTableLayout =(TableLayout)findViewById(R.id.ultraConfigTable);
            Elements tableRows = intelTable.get(0).select("tr");

            TableRow.LayoutParams textLayoutWeight1 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            TableRow.LayoutParams textLayoutWeight2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2);

            for (Element tableRow : tableRows) {
                TableRow uiTableRow = new TableRow(this);

                Elements tableData = tableRow.select("td");

                MyTextView typeTextView = new MyTextView(this);
                typeTextView.setLayoutParams(textLayoutWeight1);
                typeTextView.setText(tableData.get(0).text());
                typeTextView.setPadding(5,2,5,2);

                MyTextView detailTextView = new MyTextView(this);
                detailTextView.setLayoutParams(textLayoutWeight2);
                detailTextView.setText(tableData.get(1).text());
                detailTextView.setPadding(5,2,5,2);

                MyTextView priceTextView = new MyTextView(this);
                priceTextView.setLayoutParams(textLayoutWeight1);
                priceTextView.setText(tableData.get(2).text());
                priceTextView.setPadding(5,2,5,2);

                uiTableRow.addView(typeTextView);
                uiTableRow.addView(detailTextView);
                uiTableRow.addView(priceTextView);

                uiTableLayout.addView(uiTableRow);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RetrieveBlogContent extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("https://www.googleapis.com/blogger/v3/blogs/230842735819274721/posts/6081886220470942147?key=" + BLOGGER_API_KEY);
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
