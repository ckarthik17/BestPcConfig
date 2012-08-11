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
        update();
    }

    private void initProperties(Intent intent) {
        post = (BlogPost) intent.getSerializableExtra(HomePage.BLOG_POST);
    }

    public void update() {
        try {
            RetrieveBlogContent retrieveBlogContent = new RetrieveBlogContent();
            String responseString = retrieveBlogContent.execute(post.getPostId()).get();

            BlogContent blogContent = new BlogContent(responseString);
            ConfigTableLayout configTableLayout = new ConfigTableLayout(this, post.getTableId());

            configTableLayout.updateLayout(blogContent.parseTable(post.getTableName()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
