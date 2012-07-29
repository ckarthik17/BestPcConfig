package com.ckarthik17.bestpcconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Cache {
    private SharedPreferences cache;
    private SharedPreferences.Editor cacheEditor;

    public Cache(Context context) {
        this.cache = PreferenceManager.getDefaultSharedPreferences(context);
        this.cacheEditor = this.cache.edit();
    }

    public String getValue(String key) {
        return cache.getString(key, null);
    }

    public void setValue(String key, String value) {
        cacheEditor.putString(key, value);
        cacheEditor.commit();
    }

    public boolean contains(String key) {
        return cache.contains(key);
    }
}
