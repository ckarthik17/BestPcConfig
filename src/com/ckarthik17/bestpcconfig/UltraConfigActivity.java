package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.os.Bundle;

public class UltraConfigActivity extends Activity
{
    public static final String ULTRA_CONFIG_POST_ID = "6081886220470942147";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ultra_config);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
            String responseString = retrieveBlogContent.execute(ULTRA_CONFIG_POST_ID).get();

            BlogContent blogContent = new BlogContent(responseString);
            ConfigTableLayout configTableLayout = new ConfigTableLayout(this, R.id.ultraConfigTable);

            configTableLayout.updateLayout(blogContent.parseTable("#configTable"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
