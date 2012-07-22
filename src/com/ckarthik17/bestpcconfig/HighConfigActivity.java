package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.os.Bundle;

public class HighConfigActivity extends Activity
{
    public static final String HIGH_CONFIG_POST_ID = "1186418786118781314";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_config);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
            String responseString = retrieveBlogContent.execute(HIGH_CONFIG_POST_ID).get();

            BlogContent blogContent = new BlogContent(responseString);
            ConfigTableLayout configTableLayout = new ConfigTableLayout(this, R.id.highConfigTable);

            configTableLayout.updateLayout(blogContent.parseTable("#intelTable"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
