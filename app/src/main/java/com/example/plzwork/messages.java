package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Button btn1 = findViewById(R.id.button27);

        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        Button home = findViewById(R.id.button28);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        Button profile = findViewById(R.id.button29);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });


        Button notifications = findViewById(R.id.button25);
        notifications.setOnClickListener(v -> {
            Intent intent = new Intent(this, notifications.class);
            startActivity(intent);
        });

        Button messages = findViewById(R.id.button26);
        messages.setOnClickListener(v -> {
            Intent intent = new Intent(this, messages.class);
            startActivity(intent);
        });
    }
}