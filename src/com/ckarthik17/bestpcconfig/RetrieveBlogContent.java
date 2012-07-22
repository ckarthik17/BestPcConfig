package com.ckarthik17.bestpcconfig;

import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import static com.ckarthik17.bestpcconfig.HomePage.BLOGGER_API_KEY;
import static com.ckarthik17.bestpcconfig.HomePage.BLOG_API_URL;

public class RetrieveBlogContent extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String postId = strings[0];
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();

        HttpGet httpGet = new HttpGet(BLOG_API_URL + "/posts/" + postId + "?key=" + BLOGGER_API_KEY);
        String text = null;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();
            text = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }
}
