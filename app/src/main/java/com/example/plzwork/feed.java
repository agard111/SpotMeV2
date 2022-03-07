package com.example.plzwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feed extends AppCompatActivity {

    FirebaseDatabase user_database;
    DatabaseReference Username, Fitness_Level, Availability, Locations;
    TextView retrieveUser, retrieveFitness, retrieveAvailable, retrieveLocations;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    List<UserInfo> profilesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = this;

        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.child("Name").getValue(String.class);
                            String image = snapshot.child("Image").getValue(String.class);
                            String fitness = snapshot.child("Fitness Level").getValue(String.class);
                            String days = snapshot.child("Days Available").getValue(String.class);
                            String contact = snapshot.child("Contact Info").getValue(String.class);
                            String time = snapshot.child("Time Available").getValue(String.class);
                            String bio = snapshot.child("Bio").getValue(String.class);
                            String ID = snapshot.getKey();
                            String location = snapshot.child("Location").getValue(String.class);
                            String email = snapshot.child("Email").getValue(String.class);

                            profilesList.add(new UserInfo(name, image, fitness, days, contact, time, bio, ID, location, email));

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {                    }
                });


        //TODO: possibly move this to adapter? so this code can be reused for each scroll?
        ImageButton visit1 = findViewById(R.id.visit1);
        visit1.setOnClickListener(v -> {
            //saveData(profilesList.get(0).getName());
            Intent intent = new Intent(feed.this, private_profile.class);
            intent.putExtra("profileslist", (Parcelable) profilesList);
            startActivity(intent);
        });


        //don't touch these, these are the tab buttons
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ImageButton feed = findViewById(R.id.button12);

        feed.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        ImageButton home = findViewById(R.id.button13);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        ImageButton profile = findViewById(R.id.button14);
        Glide.with(this).load(user.getPhotoUrl()).into(profile);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });


        /*
        Todo:
        tracker index (int), push firebase data into arraylist (partitioned section)
        might have to make new object container (class)
         */
        int start = 0;

        RecyclerView rvItems = (RecyclerView) findViewById(R.id.recyclerViewProfile);

        feedAdapter adapter = new feedAdapter(profilesList);
        rvItems.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvItems.setLayoutManager(linearLayoutManager);
        EndlessScrollListener scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //TODO: call more profiles from firebase
                int curSize = adapter.getItemCount();

                view.post(new Runnable() {
                    @Override
                    public void run() {
                        //aaaa
                    }
                });
            }
        };


        rvItems.addOnScrollListener(scrollListener);

        user_database = FirebaseDatabase.getInstance();

        Username = user_database.getReference("Username");
        retrieveUser = findViewById(R.id.user1);


        Fitness_Level = user_database.getReference("Fitness Level");
        retrieveFitness = findViewById(R.id.fitness1);

        Availability = user_database.getReference("Availability");
        retrieveAvailable = findViewById(R.id.availability1);

        Locations = user_database.getReference("Locations");
        retrieveLocations = findViewById(R.id.location1);
    }

    public void loadProfile(ImageButton visit) {
        visit.setOnClickListener(v -> {
            saveData(profilesList.get(0).getName()); //TODO later: add the other fields
            Intent intent = new Intent(feed.this, private_profile.class);
            startActivity(intent);
        });
    }

    public void saveData(String name) {
        //    public void saveData(String name, String bio, String fitnessLevel, String key, String image, String Dayavailability, String time, String contactInfo) {
        SharedPreferences myPrefs = getSharedPreferences("postPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("name", name);
//        editor.putString("bio", bio);
//        editor.putString("fitnessLevel", fitnessLevel);
//        editor.putString("ID", key);
//        editor.putString("imageURL", image);
//        editor.putString("Day availability", Dayavailability);
//        editor.putString("Time available", time);
//        editor.putString("Contact Info", contactInfo);

        editor.apply();
    }

    class FetchImage extends Thread {
        String URL;
        Bitmap bitmap;
        FetchImage(String URL) {
            this.URL = URL;
        }

        @Override
        public void run() {

        }
    }


}