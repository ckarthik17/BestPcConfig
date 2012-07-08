package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomePage extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editTextBox = (EditText) findViewById(R.id.edit_message);
        String value = editTextBox.getText().toString();
        intent.putExtra(DisplayMessageActivity.TEXT_VALUE, value);

        startActivity(intent);
    }
}
