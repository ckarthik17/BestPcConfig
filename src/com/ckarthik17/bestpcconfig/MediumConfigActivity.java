package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.os.Bundle;

public class MediumConfigActivity extends Activity
{
    public static final String MEDIUM_CONFIG_POST_ID = "2196091438738422674";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medium_config);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
            String responseString = retrieveBlogContent.execute(MEDIUM_CONFIG_POST_ID).get();

            BlogContent blogContent = new BlogContent(responseString);
            ConfigTableLayout configTableLayout = new ConfigTableLayout(this, R.id.mediumConfigTable);

            configTableLayout.updateLayout(blogContent.parseTable("#intelTable"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
