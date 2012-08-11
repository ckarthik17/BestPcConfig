package com.ckarthik17.bestpcconfig;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static android.view.View.OnClickListener;

public class HomePage extends TabActivity implements OnClickListener {
    public static final String BLOGGER_API_KEY_PROPERTY = "BLOGGER_API_KEY";
    public static final String APP_TAG = "CK_BEST_PC_CONFIG";
    public static final String BLOG_API_URL = "https://www.googleapis.com/blogger/v3/blogs/230842735819274721";
    public static String BLOGGER_API_KEY;

    public static final int TAB_HEIGHT = 50;
    public static final int HIGH_CONFIG_TAB_INDEX = 1;
    private static Cache cache;

    private TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tabHost = getTabHost();

        try {
            initializeCache();
            initializeBloggerKey();
            initializeTabs();

            ImageButton refreshButton = (ImageButton)findViewById(R.id.refreshButton);
            refreshButton.setOnClickListener(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void initializeCache() {
        cache = new Cache(this);
    }

    private void initializeBloggerKey() throws IOException {
        Resources resources = this.getResources();
        AssetManager assetManager = resources.getAssets();

        InputStream inputStream = assetManager.open("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        BLOGGER_API_KEY = (String) properties.get(BLOGGER_API_KEY_PROPERTY);
    }

    private void initializeTabs() {
        TabHost.TabSpec mediumConfigTab = initializeTab("Medium (35k)", MediumConfigActivity.class);
        TabHost.TabSpec highConfigTab = initializeTab("HighEnd (60k)", HighConfigActivity.class);
        TabHost.TabSpec ultraConfigTab = initializeTab("Ultra (1L)", UltraConfigActivity.class);

        addTabs(mediumConfigTab, highConfigTab, ultraConfigTab);
        tabHost.setCurrentTab(HIGH_CONFIG_TAB_INDEX);
        //setTabHeight();
    }

    private void addTabs(TabHost.TabSpec... tabs) {
        for (TabHost.TabSpec tab : tabs) {
            tabHost.addTab(tab);
        }
    }

    private void setTabHeight() {
        for (int i=0;  i < tabHost.getTabWidget().getTabCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).getLayoutParams().height= TAB_HEIGHT;
        }
    }

    private TabHost.TabSpec initializeTab(String tabName, Class tabClass) {
        TabHost.TabSpec configTab = tabHost.newTabSpec(tabName);
        configTab.setIndicator(tabName);
        Intent intent = new Intent(this, tabClass);
        configTab.setContent(intent);
        return configTab;
    }

    public static Cache getCache() {
        return cache;
    }

    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Refreshing...")
                .setTitle("Update")
                .setCancelable(true)
                .setNeutralButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){}
                        })
                .show();
    }
}
