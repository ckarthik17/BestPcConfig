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

    public static final String MEDIUM_CONFIG_POST_ID = "2196091438738422674";
    public static final String HIGH_CONFIG_POST_ID = "1186418786118781314";
    public static final String ULTRA_CONFIG_POST_ID = "6081886220470942147";

    public static final String INTEL_TABLE = "#intelTable";
    public static final String CONFIG_TABLE = "#configTable";

    public static final int TAB_HEIGHT = 70;
    public static final int HIGH_CONFIG_TAB_INDEX = 1;
    private static Cache cache;
    public static final String BLOG_POST = "BLOG_POST";

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
        BlogPost mediumBlogPost = new BlogPost(R.layout.config_layout, R.id.configTable, INTEL_TABLE, MEDIUM_CONFIG_POST_ID);
        BlogPost highBlogPost = new BlogPost(R.layout.config_layout, R.id.configTable, INTEL_TABLE, HIGH_CONFIG_POST_ID);
        BlogPost ultraBlogPost = new BlogPost(R.layout.config_layout, R.id.configTable, CONFIG_TABLE, ULTRA_CONFIG_POST_ID);

        TabHost.TabSpec mediumConfigTab = initializeTab("Medium (35k)", ConfigActivity.class, mediumBlogPost);
        TabHost.TabSpec highConfigTab = initializeTab("HighEnd (60k)", ConfigActivity.class, highBlogPost);
        TabHost.TabSpec ultraConfigTab = initializeTab("Ultra (1L)", ConfigActivity.class, ultraBlogPost);

        addTabs(mediumConfigTab, highConfigTab, ultraConfigTab);
        tabHost.setCurrentTab(HIGH_CONFIG_TAB_INDEX);
        setTabHeight();
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

    private TabHost.TabSpec initializeTab(String tabName, Class tabClass, BlogPost blogPost) {
        TabHost.TabSpec configTab = tabHost.newTabSpec(tabName);
        configTab.setIndicator(tabName);
        Intent intent = new Intent(this, tabClass);
        intent.putExtra(BLOG_POST, blogPost);
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
