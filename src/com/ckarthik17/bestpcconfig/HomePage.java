package com.ckarthik17.bestpcconfig;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HomePage extends TabActivity {
    public static final String BLOGGER_API_KEY_PROPERTY = "BLOGGER_API_KEY";
    public static String BLOGGER_API_KEY;
    public static final String LOG_TAG = "BEST PC CONFIG";
    public static final String BLOG_API_URL = "https://www.googleapis.com/blogger/v3/blogs/230842735819274721";

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

        TabHost tabHost = getTabHost();

        TabHost.TabSpec mediumConfigTab = tabHost.newTabSpec("Medium Config");
        mediumConfigTab.setIndicator("Medium Config");
        Intent photosIntent = new Intent(this, MediumConfigActivity.class);
        mediumConfigTab.setContent(photosIntent);

        TabHost.TabSpec highConfigTab = tabHost.newTabSpec("HighEnd Config");
        highConfigTab.setIndicator("HighEnd Config");
        Intent songsIntent = new Intent(this, HighConfigActivity.class);
        highConfigTab.setContent(songsIntent);

        TabHost.TabSpec ultraConfigTab = tabHost.newTabSpec("Ultra Config");
        ultraConfigTab.setIndicator("Ultra Config");
        Intent videosIntent = new Intent(this, UltraConfigActivity.class);
        ultraConfigTab.setContent(videosIntent);

        tabHost.addTab(mediumConfigTab);
        tabHost.addTab(highConfigTab);
        tabHost.addTab(ultraConfigTab);

        tabHost.setCurrentTab(1);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height =60;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height =60;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height =60;
    }
}
