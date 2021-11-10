package com.example.plzwork;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;


public class UserProfileActivity extends AppCompatActivity {
    TextView tvUserName;
    TextView tvUserEmail;
    ImageView userImageView;
    Button btnSignOut;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();//kai code
    private DatabaseReference root = db.getReference().child("Users");//kai code
    private DatabaseReference dataReference = db.getReference();//kai code
    private ListView mListView; //kai code

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        HashMap<String, String> usermap = new HashMap<>();//kai code
        usermap.put("Name", user.getDisplayName());//kai code
        usermap.put("Email", user.getEmail());//kai code

        root.child(user.getUid()).setValue(usermap);//kai code

        dataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        tvUserName = findViewById(R.id.username);
        tvUserEmail = findViewById(R.id.useremail);
        userImageView = findViewById(R.id.userImage);
        btnSignOut = findViewById(R.id.btnLogout);


        tvUserName.setText(user.getDisplayName());
        tvUserEmail.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(userImageView);


        btnSignOut.setOnClickListener(view ->{

            mAuth.signOut();



            startActivity(new Intent(UserProfileActivity.this, MainActivity.class));

        });


    }


    private void showData(DataSnapshot snapshot) {
        int i = 0;
        for(DataSnapshot data:snapshot.getChildren()) {
            UserInfo user = new UserInfo();
            user.setName(data.child("" + ++i).getValue(UserInfo.class).getName());
            user.setEmail(data.child("" + ++i).getValue(UserInfo.class).getEmail());

            Log.d(TAG, "Name: " + user.getName());
            Log.d(TAG, "Email: " + user.getEmail());

            ArrayList<String> arr = new ArrayList<>();
            arr.add(user.getName());
            arr.add(user.getEmail());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arr);
            mListView.setAdapter(adapter);
        }
    }

}