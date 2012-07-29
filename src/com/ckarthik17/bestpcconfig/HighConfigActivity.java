package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.os.Bundle;
import org.jsoup.select.Elements;

public class HighConfigActivity extends Activity
{
    public static final String HIGH_CONFIG_POST_ID = "1186418786118781314";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_config);
        update();
    }

    private void update() {
        try {
            RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
            String responseString = retrieveBlogContent.execute(HIGH_CONFIG_POST_ID).get();

            BlogContent blogContent = new BlogContent(responseString);
            final Elements tableRows = blogContent.parseTable("#intelTable");

            ConfigTableLayout configTableLayout = new ConfigTableLayout(this, R.id.highConfigTable);
            configTableLayout.updateLayout(tableRows);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
