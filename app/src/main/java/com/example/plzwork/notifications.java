package com.example.plzwork;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Button btn1 = findViewById(R.id.button18);

        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        Button home = findViewById(R.id.button21);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        Button profile = findViewById(R.id.button22);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });


        Button notifications = findViewById(R.id.button23);
        notifications.setOnClickListener(v -> {
            Intent intent = new Intent(this, notifications.class);
            startActivity(intent);
        });

        Button messages = findViewById(R.id.button24);
        messages.setOnClickListener(v -> {
            Intent intent = new Intent(this, messages.class);
            startActivity(intent);
        });
    }
}