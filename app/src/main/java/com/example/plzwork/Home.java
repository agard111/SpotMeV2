package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.plzwork.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn1 = findViewById(R.id.feedButton);

        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);

        });

        Button user_profile = findViewById(R.id.userProfileButton);
        user_profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });
        Button dms = findViewById(R.id.button5);
        dms.setOnClickListener(v -> {
            Intent intent = new Intent(this, direct_message.class);
            startActivity(intent);
        });
        Button home = findViewById(R.id.button6);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });
    }


    /** Called when the user taps the Send button */

    public void sendMessage(View view) {
        Intent intent = new Intent(this, display_message.class);
        EditText editText = findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}