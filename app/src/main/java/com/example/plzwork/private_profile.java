package com.example.plzwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class private_profile extends AppCompatActivity {
    private ArrayList<String> currentData = new ArrayList<>();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();//get instance of the FirebaseAuth object
    FirebaseUser user = mAuth.getCurrentUser();
    Context context = this;

    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    boolean friends;
    private final DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_profile);
        loadData();
        System.out.println("hi" + currentData.get(6));

        TextView friendInfo = findViewById(R.id.friendText);

        TextView days = findViewById(R.id.DaysFree);
        days.setText("I'm free on " + currentData.get(5));


        TextView name = findViewById(R.id.username);
        name.setText(currentData.get(0));

        TextView bio = findViewById(R.id.bio);
        bio.setText(currentData.get(1));

        ImageView profilePic = findViewById(R.id.userImage);
        String url = currentData.get(4);
        Glide.with(context).load(url).into(profilePic);

        ImageButton addFriend = findViewById(R.id.addFriend);
        addFriend.setOnClickListener(v -> {
            HashMap<String, Object> usermap = new HashMap<>();
            usermap.put(user.getDisplayName(), user.getUid());
            root.child(currentData.get(3)).child("Requests").updateChildren(usermap);
            Toast.makeText(private_profile.this, "Request Sent! ", Toast.LENGTH_SHORT).show();
        });

        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //TODO: make UI run in an infinite loop to display multiple user profiles w RecyclerView

                        for (DataSnapshot snapshot : dataSnapshot.child(user.getUid()).child("Friends").getChildren()) {

                            if(snapshot.getValue().equals(currentData.get(3))){
                                friendInfo.setText("We are SpotMe Friends! Contact me at " + currentData.get(7));
                                friends = true;
                            }
                        }
                        if(!friends){
                            friendInfo.setText("We are not SpotMe Friends");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


        ImageButton home = findViewById(R.id.button10);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


        ImageButton profile = findViewById(R.id.button11);
        Glide.with(this).load(user.getPhotoUrl()).into(profile);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });


        ImageButton feed = findViewById(R.id.button4);
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
        currentData.add(myPrefs.getString("Contact Info",""));
    }
}