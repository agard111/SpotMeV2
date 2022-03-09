package com.example.plzwork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class MainActivity extends AppCompatActivity {
    //Setting instance variables for the sign-in page activity
    SignInButton btnSignIn; //sign in button
    private GoogleSignInClient mGoogleSignInClient; //instance of the google sign-in client;

    private FirebaseAuth mAuth; //Gateway to the firebase authentication API;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.btnSignIn); //sets btnSignIn to the ID of the actual sign in button

        mAuth = FirebaseAuth.getInstance(); //Gets an instance of the FirebaseAuth class

        requestGoogleSignIn(); //sign in function

        btnSignIn.setOnClickListener(view -> { //if button is clicked, run the resultLauncher function

            resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
        });


    }


    private void requestGoogleSignIn(){ //Handles the actual signing in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent intent = result.getData();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);

            try {
                //System.out.println("pog");
                // Google Sign In was successful, run the firebaseAuthWithGoogle function
                GoogleSignInAccount account = task.getResult(ApiException.class);



                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) { //If sign-in fails
                Toast.makeText(MainActivity.this, "Authentication Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    });


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        // Sign in success, update UI with the signed-in user's information

                        Intent intent = new Intent(MainActivity.this,edit_profile.class);
                        startActivity(intent);




                        Toast.makeText(MainActivity.this, "Welcome to SpotMe! Let's setup your profile ", Toast.LENGTH_SHORT).show();

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    @Override
    protected void onStart() { //If user is already signed in, go straight to the UserProfileActivity
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this, Home.class));

        }
    }

}
