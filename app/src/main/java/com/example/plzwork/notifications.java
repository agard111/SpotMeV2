package com.example.plzwork;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class notifications extends AppCompatActivity {



    FirebaseAuth mAuth = FirebaseAuth.getInstance();//get instance of the FirebaseAuth object
    FirebaseUser user = mAuth.getCurrentUser();
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();

    private final DatabaseReference root = db.getReference().child("Users");
    ArrayList<DataSnapshot> currentReqs = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        TextView friendReq = findViewById(R.id.Request);

        ImageButton btn1 = findViewById(R.id.button18);

        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        ImageButton home = findViewById(R.id.button21);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        ImageButton profile = findViewById(R.id.button22);
        Glide.with(this).load(user.getPhotoUrl()).into(profile); //Displays the user's profile picture
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });



        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //TODO: make UI run in an infinite loop to display multiple user profiles w RecyclerView

                        for (DataSnapshot snapshot : dataSnapshot.child(user.getUid()).child("Requests").getChildren()) {
                            currentReqs.add(snapshot);





                        }
                        if(currentReqs.size() > 0) {
                            friendReq.setText("New Friend Request From " + currentReqs.get(0).getKey().toString());
                        }
                        else{
                            friendReq.setText("No new Requests at this time");
                        }


                        ImageButton decline = findViewById(R.id.button24);
                        decline.setOnClickListener(v -> {
                            if(currentReqs.size() > 0) {
                                root.child(user.getUid()).child("Requests").child(currentReqs.get(0).getKey()).removeValue();
                                Toast.makeText(notifications.this, "Request Declined", Toast.LENGTH_SHORT).show();

                                friendReq.setText("Refresh to view more requests");
                            }
                            else{
                                Toast.makeText(notifications.this, "No New Requests", Toast.LENGTH_SHORT).show();

                            }
                        });

                        ImageButton accept = findViewById(R.id.button23);
                        accept.setOnClickListener(v -> {
                            if(currentReqs.size() > 0) {
                                HashMap<String, Object> usermap = new HashMap<>();
                                usermap.put(currentReqs.get(0).getKey(), currentReqs.get(0).getValue());
                                root.child(user.getUid()).child("Friends").updateChildren(usermap);

                                HashMap<String, Object> usermap2 = new HashMap<>();
                                usermap2.put(user.getDisplayName(), user.getUid());
                                String ID = (String) currentReqs.get(0).getValue();
                                root.child(ID).child("Friends").updateChildren(usermap2);

                                root.child(user.getUid()).child("Requests").child(currentReqs.get(0).getKey()).removeValue();
                                Toast.makeText(notifications.this, "Accepted", Toast.LENGTH_SHORT).show();

                                friendReq.setText("Refresh to view more requests");
                            }
                            else {
                                Toast.makeText(notifications.this, "No New Requests", Toast.LENGTH_SHORT).show();

                            }



                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


    }
}