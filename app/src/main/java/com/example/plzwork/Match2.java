package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Match2 extends AppCompatActivity {
    FirebaseDatabase user_database = FirebaseDatabase.getInstance();
    DatabaseReference mDb = user_database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    Boolean match = false;
    String uid = user.getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = this;
        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int userFit = Integer.parseInt(dataSnapshot.child(uid).child("Fitness Level").getValue(String.class));
                        String userAvail = dataSnapshot.child(uid).child("Days Available").getValue(String.class);
                        String timeAvail = dataSnapshot.child(uid).child("Time Available").getValue(String.class);

                        String[] times = timeAvail.split(" ");
                        int startTime = Integer.parseInt(times[0]);
                        int endTime = Integer.parseInt(times[times.length - 2]);

                        if(times[1].equals("PM")){
                            startTime += 12;
                        }
                        if(times[times.length - 1].equals("PM")) {
                            endTime += 12;
                        }

                        int index = 0;
                        ArrayList<DataSnapshot> matches = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (index > 2) {
                                break;
                            }

                            if (match) {
                                match = false;
                            }

                            int matchVal = 0;
                            int fitnessLevel = Integer.parseInt(snapshot.child("Fitness Level").getValue(String.class));
                            String daysAvail = snapshot.child("Days Available").getValue(String.class);

                            if (daysAvail.contains("Monday") && userAvail.contains("Monday")) {
                                matchVal += 1;
                            }
                            if (daysAvail.contains("Tuesday") && userAvail.contains("Tuesday")) {
                                matchVal += 1;
                            }
                            if (daysAvail.contains("Wednesday") && userAvail.contains("Wednesday")) {
                                matchVal += 1;
                            }
                            if (daysAvail.contains("Thursday") && userAvail.contains("Thursday")) {
                                matchVal += 1;
                            }
                            if (daysAvail.contains("Friday") && userAvail.contains("Friday")) {
                                matchVal += 1;
                            }
                            if (daysAvail.contains("Saturday") && userAvail.contains("Saturday")) {
                                matchVal += 1;
                            }
                            if (daysAvail.contains("Sunday") && userAvail.contains("Sunday")) {
                                matchVal += 1;
                            }

                            if (userFit == fitnessLevel) {
                                matchVal += 3;
                            } else if (userFit - 1 <= fitnessLevel && userFit + 1 >= fitnessLevel) {
                                matchVal += 2;
                            } else if (userFit - 2 <= fitnessLevel && userFit + 2 >= fitnessLevel) {
                                matchVal += 1;
                            }

                            String tAvail = snapshot.child("Time Available").getValue(String.class);
                            String[] t = tAvail.split(" ");

                            int sTime = Integer.parseInt(times[0]);
                            int eTime = Integer.parseInt(times[times.length - 2]);

                            if (times[1].equals("PM")) {
                                sTime += 12;
                            }
                            if (times[times.length - 1].equals("PM")) {
                                eTime += 12;
                            }

                            if (sTime <= startTime && eTime >= eTime) {
                                matchVal += 2;
                            }
                            else if (startTime < sTime && endTime > endTime) {
                                matchVal += 2;
                            }
                            else if (sTime < startTime && eTime > startTime) {
                                matchVal += 2;
                            }
                            else if (sTime < endTime && eTime > endTime) {
                                matchVal += 2;
                            }
                            else if (startTime <= sTime && sTime < endTime) {
                                matchVal += 2;
                            }
                            else if (startTime < eTime && endTime > eTime) {
                                matchVal += 2;
                            }

                            if (matchVal >= 5) {
                                match = true;
                            }

                            if (match) {
                                matches.add(snapshot);
//                                bios.get(index).setText(snapshot.child("Name").getValue(String.class));
//                                index += 1;
                            }
                        }

                        TextView user1 = findViewById(R.id.username3);
                        user1.setText("Username: " + matches.get(0).child("Name").getValue(String.class));

                        TextView bio1 = findViewById(R.id.Bio);
                        bio1.setText(matches.get(0).child("Bio").getValue(String.class));

                        ImageButton btn1 = findViewById(R.id.imageButton);
                        Glide.with(context).load(matches.get(0).child("Image").getValue(String.class)).into(btn1);
                        btn1.setOnClickListener(v -> {
                            saveData(matches.get(0).child("Name").getValue(String.class), matches.get(0).child("Bio").getValue(String.class), matches.get(0).child("Fitness Level").getValue(String.class), matches.get(0).getKey(), matches.get(0).child("Image").getValue(String.class), matches.get(0).child("Days Available").getValue(String.class), matches.get(0).child("Time Available").getValue(String.class), matches.get(0).child("Contact Info").getValue(String.class));
                            Intent intent = new Intent(Match2.this, private_profile.class);
                            startActivity(intent);
                        });

                        TextView user2 = findViewById(R.id.username2);
                        user2.setText("Username: " + matches.get(1).child("Name").getValue(String.class));

                        TextView bio2 = findViewById(R.id.Bio2);
                        bio2.setText(matches.get(1).child("Bio").getValue(String.class));

                        ImageButton btn2 = findViewById(R.id.imageButton2);
                        Glide.with(context).load(matches.get(1).child("Image").getValue(String.class)).into(btn2);
                        btn2.setOnClickListener(v -> {
                            saveData(matches.get(1).child("Name").getValue(String.class), matches.get(1).child("Bio").getValue(String.class), matches.get(1).child("Fitness Level").getValue(String.class), matches.get(1).getKey(), matches.get(1).child("Image").getValue(String.class), matches.get(1).child("Days Available").getValue(String.class), matches.get(1).child("Time Available").getValue(String.class), matches.get(1).child("Contact Info").getValue(String.class));
                            Intent intent = new Intent(Match2.this, private_profile.class);
                            startActivity(intent);
                        });

                        TextView user3 = findViewById(R.id.textView22);
                        user3.setText("Username: " + matches.get(2).child("Name").getValue(String.class));

                        TextView bio3 = findViewById(R.id.textView23);
                        bio3.setText(matches.get(2).child("Bio").getValue(String.class));

                        ImageButton btn3 = findViewById(R.id.imageButton11);
                        Glide.with(context).load(matches.get(2).child("Image").getValue(String.class)).into(btn3);
                        btn3.setOnClickListener(v -> {
                            saveData(matches.get(2).child("Name").getValue(String.class), matches.get(2).child("Bio").getValue(String.class), matches.get(2).child("Fitness Level").getValue(String.class), matches.get(2).getKey(), matches.get(2).child("Image").getValue(String.class), matches.get(2).child("Days Available").getValue(String.class), matches.get(2).child("Time Available").getValue(String.class), matches.get(2).child("Contact Info").getValue(String.class));
                            Intent intent = new Intent(Match2.this, private_profile.class);
                            startActivity(intent);
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.print("failed");
                    }
                });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match2);

        ImageButton feed = findViewById(R.id.feedButton);

        feed.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        ImageButton home = findViewById(R.id.button6);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        ImageButton profile = findViewById(R.id.userProfileButton);
        Glide.with(this).load(user.getPhotoUrl()).into(profile);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
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