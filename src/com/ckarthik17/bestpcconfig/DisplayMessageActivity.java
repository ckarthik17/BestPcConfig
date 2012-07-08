package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {
    public static final String TEXT_VALUE = "TEXT_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String message = intent.getStringExtra(TEXT_VALUE);

        TextView textView = new TextView(this);
        textView.setText(message);

        setContentView(textView);
    }
}
