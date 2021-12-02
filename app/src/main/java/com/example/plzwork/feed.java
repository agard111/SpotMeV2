package com.example.plzwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView retrieveUser, retrieveFitness, retrieveAvailable, retrieveLocations;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    private ImageView profilePic;
    private ValueEventListener postListener;
    private ImageButton bn;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> days2 = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> bios = new ArrayList<>();
    ArrayList<String> IDs = new ArrayList<>();
    ArrayList<String> fitnessLevels2 = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<String> contacts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = this;
        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //TODO: make UI run in an infinite loop to display multiple user profiles w RecyclerView
//                        List<ImageView> profilePictures = new ArrayList<>();
//                        profilePic = findViewById(R.id.feedImg1); profilePictures.add(profilePic);
//                        profilePic = findViewById(R.id.feedImg2); profilePictures.add(profilePic);
//                        profilePic = findViewById(R.id.feedImg3); profilePictures.add(profilePic);
//                        profilePic = findViewById(R.id.feedImg4); profilePictures.add(profilePic);
//                        profilePic = findViewById(R.id.feedImg5); profilePictures.add(profilePic);
//                        profilePic = findViewById(R.id.feedImg6); profilePictures.add(profilePic);
//                        profilePic = findViewById(R.id.feedImg7); profilePictures.add(profilePic);

                        List<TextView> usernames = new ArrayList<>();
                        retrieveUser = findViewById(R.id.user1); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user2); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user3); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user4); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user5); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user6); usernames.add(retrieveUser);
                        retrieveUser = findViewById(R.id.user7); usernames.add(retrieveUser);

                        List<TextView> fitnessLevels = new ArrayList<>();
                        retrieveFitness = findViewById(R.id.fitness1); fitnessLevels.add(retrieveFitness);
                        retrieveFitness = findViewById(R.id.fitness2); fitnessLevels.add(retrieveFitness);
                        retrieveFitness = findViewById(R.id.fitness3); fitnessLevels.add(retrieveFitness);
                        retrieveFitness = findViewById(R.id.fitness4); fitnessLevels.add(retrieveFitness);
                        retrieveFitness = findViewById(R.id.fitness5); fitnessLevels.add(retrieveFitness);
                        retrieveFitness = findViewById(R.id.fitness6); fitnessLevels.add(retrieveFitness);
                        retrieveFitness = findViewById(R.id.fitness7); fitnessLevels.add(retrieveFitness);

                        List<TextView> availabilities = new ArrayList<>();
                        retrieveAvailable = findViewById(R.id.availability1); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability2); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability3); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability4); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability5); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability6); availabilities.add(retrieveAvailable);
                        retrieveAvailable = findViewById(R.id.availability7); availabilities.add(retrieveAvailable);

                        List<TextView> locations = new ArrayList<>();
                        retrieveLocations = findViewById(R.id.location1); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location2); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location3); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location4); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location5); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location6); locations.add(retrieveLocations);
                        retrieveLocations = findViewById(R.id.location7); locations.add(retrieveLocations);

                        int index = 0;
                        ImageButton visit1 = findViewById(R.id.visit1);
                        ImageButton visit2 = findViewById(R.id.visit2);
                        ImageButton visit3 = findViewById(R.id.visit3);
                        ImageButton visit4 = findViewById(R.id.visit4);
                        ImageButton visit5 = findViewById(R.id.visit5);
                        ImageButton visit6 = findViewById(R.id.visit6);
                        ImageButton visit7 = findViewById(R.id.visit7);

                        List<ImageButton> visit = new ArrayList<>();
                        visit.add(visit1);
                        visit.add(visit2);
                        visit.add(visit3);
                        visit.add(visit4);
                        visit.add(visit5);
                        visit.add(visit6);
                        visit.add(visit7);

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String username = snapshot.child("Name").getValue(String.class);
                            names.add(username);
                            retrieveUser = usernames.get(index);
                            retrieveUser.setText("Username: " + username);


                            String url = snapshot.child("Image").getValue(String.class);
                            urls.add(url);
//                            profilePic = profilePictures.get(index);
                            bn = visit.get(index);
//                            Glide.with(context).load(url).into(profilePic);
                            Glide.with(context).load(url).into(bn);


                            String fitness = snapshot.child("Fitness Level").getValue(String.class);
                            fitnessLevels2.add(fitness);
                            retrieveFitness = fitnessLevels.get(index);
                            retrieveFitness.setText("Fitness Level: " + fitness);

                            String days = snapshot.child("Days Available").getValue(String.class); //POG IT WORKS
                            days2.add(days);

                            String contact = snapshot.child("Contact Info").getValue(String.class);
                            contacts.add(contact);

                            retrieveAvailable = availabilities.get(index);
                            retrieveAvailable.setText("Days Available: " + days);

                            String time = snapshot.child("Time Available").getValue(String.class);//POG IT WORKS
                            times.add(time);
                            String bio = snapshot.child("Bio").getValue(String.class);
                            bios.add(bio);
                            String ID = snapshot.getKey();
                            IDs.add(ID);



                            String location = snapshot.child("Location").getValue(String.class);
                            retrieveLocations = locations.get(index);
                            retrieveLocations.setText("Location: " + location);


//                            if(index < profilePictures.size() - 1)  //TODO: recyclerview :(
                                index++;
                        }


                        visit1.setOnClickListener(v -> {
                            saveData(names.get(0),bios.get(0),fitnessLevels2.get(0),IDs.get(0),urls.get(0),days2.get(0), times.get(0), contacts.get(0));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);

                        });

                        visit2.setOnClickListener(v -> {
                            saveData(names.get(1),bios.get(1),fitnessLevels2.get(1),IDs.get(1),urls.get(1),days2.get(1), times.get(1), contacts.get(1));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);


                        });

                        visit3.setOnClickListener(v -> {
                            saveData(names.get(2),bios.get(2),fitnessLevels2.get(2),IDs.get(2),urls.get(2),days2.get(2), times.get(2), contacts.get(2));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);


                        });

                        visit4.setOnClickListener(v -> {
                            saveData(names.get(3),bios.get(3),fitnessLevels2.get(3),IDs.get(3),urls.get(3),days2.get(3), times.get(3), contacts.get(3));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);


                        });

                        visit5.setOnClickListener(v -> {
                            saveData(names.get(4),bios.get(4),fitnessLevels2.get(4),IDs.get(4),urls.get(4),days2.get(4), times.get(4), contacts.get(4));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);


                        });

                        visit6.setOnClickListener(v -> {
                            saveData(names.get(5),bios.get(5),fitnessLevels2.get(5),IDs.get(5),urls.get(5),days2.get(5), times.get(5), contacts.get(5));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);

                        });

                        visit7.setOnClickListener(v -> {
                            saveData(names.get(6),bios.get(6),fitnessLevels2.get(6),IDs.get(6),urls.get(6),days2.get(6), times.get(6), contacts.get(6));
                            Intent intent = new Intent(feed.this, private_profile.class);
                            startActivity(intent);

                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

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

    public void saveData(String name, String bio, String fitnessLevel, String key,String image, String Dayavailability, String time, String contactInfo){
        SharedPreferences myPrefs = getSharedPreferences("postPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("name",name);
        editor.putString("bio",bio);
        editor.putString("fitnessLevel",fitnessLevel);
        editor.putString("ID",key);
        editor.putString("imageURL",image);
        editor.putString("Day availability",Dayavailability);
        editor.putString("Time available",time);
        editor.putString("Contact Info",contactInfo);

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