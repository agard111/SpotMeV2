package com.example.plzwork;

import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    TextView tvUserName; //Shows the current user's name pulled from their google account
    TextView tvUserEmail; //shows the current user's email
    ImageView userImageView; //shows the current user's profile picture
    ImageButton btnSignOut; //signOut button (in progress I still need to make it so that the user can choose
    //which google account they want to log in with after signing out but it works for now)
    //private final FirebaseDatabase db = FirebaseDatabase.getInstance();//kai code
    //private final DatabaseReference root = db.getReference().child("Users");//kai code
    //private final DatabaseReference dataReference = db.getReference();//kai code
    //private ListView mListView; //kai code




    FirebaseAuth mAuth = FirebaseAuth.getInstance();//get instance of the FirebaseAuth object
    FirebaseUser user = mAuth.getCurrentUser(); //Get the current user from the firebase API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvUserName = findViewById(R.id.username); //Sets variables equal to the respective UI IDs
        tvUserEmail = findViewById(R.id.useremail);
        userImageView = findViewById(R.id.userImage);
        btnSignOut = findViewById(R.id.btnLogout);

        tvUserName.setText(user.getDisplayName()); //Displays the user's name and email
        tvUserEmail.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(userImageView); //Displays the user's profile picture

        btnSignOut.setOnClickListener(view ->{
            GoogleSignIn.getClient(getApplicationContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();
            mAuth.signOut();//mGoogleSignInClient.signOut(); //Signs out and restarts the main activity
            startActivity(new Intent(UserProfileActivity.this, MainActivity.class));

        });

        ImageButton btn1 = findViewById(R.id.button4);

        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });

        ImageButton editProfileButton = findViewById(R.id.editProfileButton);

        editProfileButton.setOnClickListener(v -> {

            Intent intent = new Intent(this, edit_profile.class);

            startActivity(intent);
        });

        ImageButton home = findViewById(R.id.button10);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        ImageButton profile = findViewById(R.id.button11);
        Glide.with(this).load(user.getPhotoUrl()).into(profile);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });
    }

    /**

    private void showData(DataSnapshot snapshot) { //Kai code
        for(DataSnapshot data:snapshot.getChildren()) {

            arrayUser.SetName(data.child(user.getUid()).getValue(UserInfo.class).GetName()); //Gets name and email of the user from Firebase
            arrayUser.SetEmail(data.child(user.getUid()).getValue(UserInfo.class).GetEmail());

            Log.d(TAG, "Name: " + arrayUser.GetName());
            Log.d(TAG, "Email: " + arrayUser.GetEmail());

            ArrayList<String> arr = new ArrayList<>();
            arr.add(arrayUser.GetName()); //Puts the name and email into an array for each user in Firebase
            arr.add(arrayUser.GetEmail());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arr);
            //mListView.setAdapter(adapter); This line makes the app crash, works fine without it. Not sure what the line does tbh
        }
    }
     */


}