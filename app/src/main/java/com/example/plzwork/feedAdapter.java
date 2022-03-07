package com.example.plzwork;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class feedAdapter extends
        RecyclerView.Adapter<feedAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<UserInfo> UserList;

    // Pass in the contact array into the constructor
    public feedAdapter(List<UserInfo> contacts) {
        UserList = contacts;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable for any view that will be set as you render a row

        private TextView retrieveUser, retrieveFitness, retrieveAvailable, retrieveLocations;
        private ImageButton visit;
        // We also create a constructor that accepts the entire item row and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used to access the context from any ViewHolder instance.
            super(itemView);
            retrieveUser = (TextView)itemView.findViewById(R.id.user1);
            retrieveFitness = itemView.findViewById(R.id.fitness1);
            retrieveAvailable = itemView.findViewById(R.id.availability1);
            retrieveLocations = itemView.findViewById(R.id.location1);
            visit = itemView.findViewById(R.id.visit1);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.activity_feed, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        UserInfo user = UserList.get(position);


    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }
}