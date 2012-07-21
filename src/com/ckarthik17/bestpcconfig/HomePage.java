package com.ckarthik17.bestpcconfig;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class HomePage extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
