package com.example.plzwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class UserProfileActivity extends AppCompatActivity {
    TextView tvUserName;
    TextView tvUserEmail;
    ImageView userImageView;
    Button btnSignOut;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();//kai code
    private DatabaseReference root = db.getReference().child("Users");//kai code
    //private DatabaseReference dataReference = db.getReference();//kai code

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        HashMap<String, String> usermap = new HashMap<>();//kai code
        usermap.put("Name", user.getDisplayName());//kai code
        usermap.put("Email", user.getEmail());//kai code

        root.child("" + 0).setValue(usermap);


        tvUserName = findViewById(R.id.username);
        tvUserEmail = findViewById(R.id.useremail);
        userImageView = findViewById(R.id.userImage);
        btnSignOut = findViewById(R.id.btnLogout);


        tvUserName.setText(user.getDisplayName());
        tvUserEmail.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(userImageView);


        btnSignOut.setOnClickListener(view ->{
            //mAuth.signOut();
            mAuth.signOut();



            startActivity(new Intent(UserProfileActivity.this, MainActivity.class));

        });

















    }
}