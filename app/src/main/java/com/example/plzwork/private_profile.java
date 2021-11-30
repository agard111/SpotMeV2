package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class private_profile extends AppCompatActivity {
    private ArrayList<String> currentData = new ArrayList<>();
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_profile);
        loadData();


        TextView name = findViewById(R.id.username);
        name.setText(currentData.get(0));

        TextView bio = findViewById(R.id.bio);
        bio.setText(currentData.get(1));

        ImageView profilePic = findViewById(R.id.userImage);
        String url = currentData.get(4);
        Glide.with(context).load(url).into(profilePic);



        Button home = findViewById(R.id.button10);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


        Button profile = findViewById(R.id.button11);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });


        Button feed = findViewById(R.id.button4);
        feed.setOnClickListener(v -> {
            Intent intent = new Intent(this, feed.class);
            startActivity(intent);
        });
    }

    public void loadData(){
        SharedPreferences myPrefs = getSharedPreferences("postPrefs",MODE_PRIVATE);
        currentData.add(myPrefs.getString("name",""));
        currentData.add(myPrefs.getString("bio",""));
        currentData.add(myPrefs.getString("fitnessLevel",""));
        currentData.add(myPrefs.getString("ID",""));
        currentData.add(myPrefs.getString("imageURL",""));
        currentData.add(myPrefs.getString("Day availability",""));
        currentData.add(myPrefs.getString("Time Available",""));
    }
}