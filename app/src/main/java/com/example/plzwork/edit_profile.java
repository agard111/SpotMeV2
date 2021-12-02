package com.example.plzwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;

public class edit_profile extends AppCompatActivity {


    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private final DatabaseReference root = db.getReference().child("Users");

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();


    TextView textView;
    boolean[] selectedDays;
    ArrayList<Integer> daylist = new ArrayList<>();
    String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};
    StringBuilder stringDays;

    TextView textView2;
    boolean[] selectedLanguage;
    ArrayList<Integer> timeList = new ArrayList<>();
    String[] timeArray = {"5 AM - 8 AM", "8 AM - 11 AM", "11 AM - 2PM", "2PM - 5PM", "5PM - 8PM", "8PM - 11PM", "11PM - 2 AM", "2 AM - 5 AM"};
    StringBuilder stringTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        selectedLanguage = new boolean[timeArray.length];

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);

                // set title
                builder.setTitle("Select Time");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(timeArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            timeList.add(i);
                            // Sort array list
                            Collections.sort(timeList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            timeList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder

                        stringTime = new StringBuilder();

                        // use for loop
                        for (int j = 0; j < timeList.size(); j++) {
                            // concat array value
                            stringTime.append(timeArray[timeList.get(j)]);
                            // check condition
                            if (j != timeList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringTime.append(", ");
                            }
                        }
                        // set text on textView
                        textView2.setText(stringTime.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguage.length; j++) {
                            // remove all selection
                            selectedLanguage[j] = false;
                            // clear language list
                            timeList.clear();
                            // clear text view value
                            textView2.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });


        // initialize selected language array
        selectedDays = new boolean[dayArray.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);



                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(dayArray, selectedDays, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            daylist.add(i);
                            // Sort array list
                            Collections.sort(daylist);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            daylist.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        stringDays = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < daylist.size(); j++) {
                            // concat array value
                            stringDays.append(dayArray[daylist.get(j)]);
                            // check condition
                            if (j != daylist.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringDays.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringDays.toString());

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedDays.length; j++) {
                            // remove all selection
                            selectedDays[j] = false;
                            // clear language list
                            daylist.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });



        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(v -> {

            EditText fitnessLevel = findViewById(R.id.fitness_level);
            EditText zipcode = findViewById(R.id.zipcode);
            EditText contactInfo = findViewById(R.id.contactInfo);
            EditText Bio = findViewById(R.id.bio);
            HashMap<String, Object> usermap = new HashMap<>();


            usermap.put("Name", user.getDisplayName());
            usermap.put("Bio", String.valueOf(Bio.getText()));
            usermap.put("Email",user.getEmail());
            usermap.put("Image", user.getPhotoUrl().toString());


            usermap.put("Fitness Level", String.valueOf(fitnessLevel.getText()));
            usermap.put("Location", String.valueOf(zipcode.getText()));
            usermap.put("Contact Info", String.valueOf(contactInfo.getText()));
            usermap.put("Days Available", stringDays.toString());
            usermap.put("Time Available",stringTime.toString());


            root.child(user.getUid()).updateChildren(usermap);

            Toast.makeText(edit_profile.this, "Saved! ", Toast.LENGTH_SHORT).show();
            finish();

        });

        Button btn1 = findViewById(R.id.feedButton);
        btn1.setOnClickListener(v -> {

            Intent intent = new Intent(this, feed.class);

            startActivity(intent);
        });



        Button home = findViewById(R.id.homeButton);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });



        Button profile = findViewById(R.id.userProfileButton);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });
    }
}