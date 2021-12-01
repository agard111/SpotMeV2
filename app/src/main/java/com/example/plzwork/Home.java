package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.plzwork.MESSAGE";
    boolean found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton search = findViewById(R.id.search);
        search.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editTextTextPersonName);


            FirebaseDatabase.getInstance().getReference().child("Users")
                    .addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //TODO: make UI run in an infinite loop to display multiple user profiles w RecyclerView

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if(editText.getText().toString().equals(snapshot.child("Name").getValue(String.class))){
                                    String username = snapshot.child("Name").getValue(String.class);
                                    String url = snapshot.child("Image").getValue(String.class);
                                    String fitness = snapshot.child("Fitness Level").getValue(String.class);
                                    String bio = snapshot.child("Bio").getValue(String.class);
                                    String time = snapshot.child("Time Available").getValue(String.class);
                                    String days = snapshot.child("Days Available").getValue(String.class);
                                    String contact = snapshot.child("Contact Info").getValue(String.class);
                                    saveData(username,bio,fitness,snapshot.getKey(),url,days,time, contact); //just call save data for whichever profile the user clicks on
                                    found = true;


                                    Intent intent = new Intent(Home.this, private_profile.class);
                                    startActivity(intent);

                                }

                            }

                            if(!found){
                                Toast.makeText(Home.this, "User Not Found" , Toast.LENGTH_SHORT).show();

                            }

                        }
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


        });



        ImageButton btn1 = findViewById(R.id.feedButton);

        btn1.setOnClickListener(v -> {
            Intent intent = new Intent(this, feed.class);
            startActivity(intent);


        });

        ImageButton user_profile = findViewById(R.id.userProfileButton);
        user_profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });
        ImageButton notifs = findViewById(R.id.button5);
        notifs.setOnClickListener(v -> {
            Intent intent = new Intent(this, notifications.class);
            startActivity(intent);
        });
        ImageButton home = findViewById(R.id.button6);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });
    }


    public void saveData(String name, String bio, String fitnessLevel, String key,String image, String Dayavailability, String time, String ContactInfo){
        SharedPreferences myPrefs = getSharedPreferences("postPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("name",name);
        editor.putString("bio",bio);
        editor.putString("fitnessLevel",fitnessLevel);
        editor.putString("ID",key);
        editor.putString("imageURL",image);
        editor.putString("Day availability",Dayavailability);
        editor.putString("Time available",time);
        editor.putString("Contact Info", ContactInfo);

        editor.apply();
    }




}