package com.example.plzwork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feed extends AppCompatActivity {

    FirebaseDatabase user_database;
    DatabaseReference Username, Fitness_Level, Availability, Locations;
    private TextView retrieveUser, retrieveFitness, retrieveAvailable, retrieveLocations;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ImageView profilePic;
    private ValueEventListener postListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = this;
        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //TODO: make UI run in an infinite loop to display multiple user profiles w RecyclerView
                        List<ImageView> profilePictures = new ArrayList<>();
                        profilePic = findViewById(R.id.feedImg1); profilePictures.add(profilePic);
                        profilePic = findViewById(R.id.feedImg2); profilePictures.add(profilePic);
                        profilePic = findViewById(R.id.feedImg3); profilePictures.add(profilePic);
                        profilePic = findViewById(R.id.feedImg4); profilePictures.add(profilePic);
                        profilePic = findViewById(R.id.feedImg5); profilePictures.add(profilePic);
                        profilePic = findViewById(R.id.feedImg6); profilePictures.add(profilePic);

                        List<TextView> usernames = new ArrayList<>();
                        retrieveUser = findViewById(R.id.user1); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user2); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user3); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user4); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user5); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user6); usernames.add(retrieveUser);

                        List<TextView> fitnessLevels = new ArrayList<>();
                        retrieveFitness = findViewById(R.id.fitness1); fitnessLevels.add(retrieveUser);
                        retrieveFitness = findViewById(R.id.fitness2); fitnessLevels.add(retrieveUser);
                        retrieveFitness = findViewById(R.id.fitness3); fitnessLevels.add(retrieveUser);
                        retrieveFitness = findViewById(R.id.fitness4); fitnessLevels.add(retrieveUser);
                        retrieveFitness = findViewById(R.id.fitness5); fitnessLevels.add(retrieveUser);
                        retrieveFitness = findViewById(R.id.fitness6); fitnessLevels.add(retrieveUser);

                        List<TextView> availabilities = new ArrayList<>();
                        retrieveAvailable = findViewById(R.id.availability1); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability2); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability3); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability4); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability5); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability6); availabilities.add(retrieveAvailable);

                        List<TextView> locations = new ArrayList<>();
                        retrieveLocations = findViewById(R.id.location1); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location2); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location3); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location4); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location5); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location6); locations.add(retrieveLocations);

                        int index = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String username = snapshot.child("Name").getValue(String.class);
                            retrieveUser = usernames.get(index);
                            retrieveUser.setText("Username: " + username);

                            String url = snapshot.child("Image").getValue(String.class);

                            profilePic = profilePictures.get(index);
                            Glide.with(context).load(url).into(profilePic);


                            String fitness = snapshot.child("Fitness Level").getValue(String.class);
                            retrieveFitness = fitnessLevels.get(index);
                            retrieveFitness.setText("Fitness Level: " + fitness);

                            String days = snapshot.child("Days Available").getValue(String.class); //POG IT WORKS
                            retrieveAvailable = availabilities.get(index);
                            retrieveAvailable.setText("Days Available: " + days);


                            String location = snapshot.child("Location").getValue(String.class);
                            retrieveLocations = locations.get(index);
                            retrieveLocations.setText("Location: " + location);

                            if(index < profilePictures.size() - 1)  //TODO: recyclerview :(
                                index++;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        Button feed = findViewById(R.id.button12);

        feed.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        Button home = findViewById(R.id.button13);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button profile = findViewById(R.id.button14);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });

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

//    private class LoadImage {
//        ImageView img;
//        public LoadImage(ImageView img) extends AsyncTask<String, Void, Bitmap>  {
//            this.img = img;
//        }
//
//        @Override
//        protected
//    }
}