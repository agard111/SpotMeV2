package com.example.plzwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feed extends AppCompatActivity {

    FirebaseDatabase user_database;

    DatabaseReference Username;

    DatabaseReference Fitness_Level;

    DatabaseReference Availability;

    DatabaseReference Locations;

    private TextView retrieveUser;

    private TextView retrieveFitness;

    private TextView retrieveAvailable;

    private TextView retrieveLocations;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        retrieveUser = findViewById(R.id.textView5);
        getUser();

        Fitness_Level = user_database.getReference("Fitness Level");
        retrieveFitness = findViewById(R.id.textView7);
        getFitness();

        Availability = user_database.getReference("Availability");
        retrieveAvailable = findViewById(R.id.textView8);
        getAvailability();

        Locations = user_database.getReference("Locations");
        retrieveLocations = findViewById(R.id.textView9);
        getLocation();

    }

    private void getLocation() {
        Locations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String locations = snapshot.getValue(String.class);
                retrieveLocations.setText(locations);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(feed.this, "Failed to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAvailability() {
        Availability.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String availability = snapshot.getValue(String.class);
                retrieveAvailable.setText(availability);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(feed.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFitness() {
        Fitness_Level.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fitness = snapshot.getValue(String.class);
                retrieveFitness.setText(fitness);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(feed.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getUser() {
        Username.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                retrieveUser.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(feed.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}