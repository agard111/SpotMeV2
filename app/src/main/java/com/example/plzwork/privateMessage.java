package com.example.plzwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class privateMessage extends AppCompatActivity {
    private ArrayList<String> currentData = new ArrayList<>();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");



    FirebaseAuth mAuth = FirebaseAuth.getInstance();//get instance of the FirebaseAuth object
    FirebaseUser user = mAuth.getCurrentUser();
    Context context = this;
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    boolean friends;

    private final DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);
        loadData();
        TextView msgName = findViewById(R.id.textView6);
        EditText msgTxt = findViewById(R.id.msgBox);
        msgName.setText("Message " + currentData.get(0));


        Button msg = findViewById(R.id.msgBtn);
        msg.setOnClickListener(v -> {
            LocalDateTime now = LocalDateTime.now();

            HashMap<String, Object> usermap = new HashMap<>();
            usermap.put(dtf.format(now), String.valueOf(msgTxt.getText()));
            //root.child(currentData.get(3)).child("Incoming Messages").updateChildren(usermap);
            root.child(currentData.get(3)).child("Incoming Messages").child(user.getDisplayName()).updateChildren(usermap);
            Toast.makeText(privateMessage.this, "Sent! ", Toast.LENGTH_SHORT).show();
            msgTxt.setText("");



        });
    }

//

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

