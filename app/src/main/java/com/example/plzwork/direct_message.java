package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class direct_message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message);

        Button btn1 = findViewById(R.id.button15);

        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        Button home = findViewById(R.id.button16);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        Button profile = findViewById(R.id.button30);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });

        Button notification = findViewById(R.id.button40);
        notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, notifications.class);
            startActivity(intent);
        });

        Button messages = findViewById(R.id.button31);
        messages.setOnClickListener(v -> {
            Intent intent = new Intent(this, messages.class);
            startActivity(intent);
        });


    }
}