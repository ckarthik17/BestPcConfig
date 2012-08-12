package com.ckarthik17.bestpcconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.util.Date;

public class PostCache {
    private SharedPreferences cache;
    private SharedPreferences.Editor cacheEditor;

    public PostCache(Context context) {
        this.cache = PreferenceManager.getDefaultSharedPreferences(context);
        this.cacheEditor = this.cache.edit();
    }

    public String getPost(BlogPost blogPost) {
        String postId = blogPost.getPostId();
        if(!contains(postId)) {
            update(postId);
        }
        return getValue(postId);
    }

    public void update(String postId) {
        try {
            RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
            String response = retrieveBlogContent.execute(postId).get();
            setValue(postId, response);
            setValue(HomePage.SYNCED_DATE_TIME, DateFormat.getDateTimeInstance().format(new Date()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getValue(String key) {
        return cache.getString(key, null);
    }

    private void setValue(String key, String value) {
        cacheEditor.putString(key, value);
        cacheEditor.commit();
    }

    private boolean contains(String key) {
        return cache.contains(key) && getValue(key) != null;
    }
}
