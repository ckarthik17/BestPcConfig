package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ConfigActivity extends Activity {
    private BlogPost post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProperties(getIntent());
        setContentView(post.getLayoutId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private void initProperties(Intent intent) {
        post = (BlogPost) intent.getSerializableExtra(HomePage.BLOG_POST);
    }

    public void update() {
        try {
            ConfigTableLayout configTableLayout = new ConfigTableLayout(this, post.getTableId());
            BlogContent blogContent = new BlogContent(HomePage.getPostCache().getPost(post));
            configTableLayout.updateLayout(blogContent.parseTable(post.getTableName()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
