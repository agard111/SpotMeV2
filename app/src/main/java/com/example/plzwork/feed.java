package com.example.plzwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

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

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                            String username = snapshot.child("Name").getValue(String.class);
                            retrieveUser = findViewById(R.id.textView5);
                            retrieveUser.setText("Name: " + username);



                            String url = snapshot.child("Image").getValue(String.class);
                            profilePic = findViewById(R.id.feedImg1);
                            Glide.with(context).load(url).into(profilePic);


                            String fitness = snapshot.child("Fitness Level").getValue(String.class);
                            retrieveFitness = findViewById(R.id.textView7);
                            retrieveFitness.setText("Fitness Level: " + fitness);

                            String bio = snapshot.child("Bio").getValue(String.class);

                            String time = snapshot.child("Time Available").getValue(String.class);


                            String days = snapshot.child("Days Available").getValue(String.class); //POG IT WORKS
                            retrieveAvailable = findViewById(R.id.textView8);
                            retrieveAvailable.setText("Days Available: " + days);

                            
                            String location = snapshot.child("Location").getValue(String.class);
                            retrieveLocations = findViewById(R.id.textView9);
                            retrieveLocations.setText("Location: " + location);


                            saveData(username,bio,fitness,snapshot.getKey(),url,days,time); //just call save data for whichever profile the user clicks on
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

        Button viewProfile = findViewById(R.id.button32);
        viewProfile.setOnClickListener(v -> {

            Intent intent = new Intent(this, private_profile.class);
            startActivity(intent);
        });

        Button profile = findViewById(R.id.button14);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });

        user_database = FirebaseDatabase.getInstance();

        Username = user_database.getReference("Username");
        retrieveUser = findViewById(R.id.textView5);


        Fitness_Level = user_database.getReference("Fitness Level");
        retrieveFitness = findViewById(R.id.textView7);

        Availability = user_database.getReference("Availability");
        retrieveAvailable = findViewById(R.id.textView8);


        Locations = user_database.getReference("Locations");
        retrieveLocations = findViewById(R.id.textView9);


    }


    public void saveData(String name, String bio, String fitnessLevel, String key,String image, String Dayavailability, String time){
        SharedPreferences myPrefs = getSharedPreferences("postPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("name",name);
        editor.putString("bio",bio);
        editor.putString("fitnessLevel",fitnessLevel);
        editor.putString("ID",key);
        editor.putString("imageURL",image);
        editor.putString("Day availability",Dayavailability);
        editor.putString("Time available",time);

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