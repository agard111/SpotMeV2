package com.example.plzwork;

import java.io.Serializable;
import java.util.*;

class UserInfo implements Serializable {
    String userName, name, profileurl, location, email, phoneNumber, fitnessLevel, userID, days, contact, time, bio;
    ArrayList<String> friendsList;

    public UserInfo() {

    }

    //TODO: implement username (not just name) and phone number, add friends list
    public UserInfo(String name, String image, String fitness, String days, String contact,
                                 String time, String bio, String ID, String location, String email) {
        this.name = name;
        this.profileurl = image;
        this.location = location;
        this.email = email;
        this.fitnessLevel = fitness;
        this.userID = ID;
        this.days = days;
        this.contact = contact;
        this.time = time;
        this.bio = bio;
        this.userID = ID;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String input) {
        email = input;
        // TODO update this to verify that a valid email is used
        // Maybe take the email used for Google Auth
    }

    public void setPhoneNumber(String input) {
        phoneNumber = input;
    }

    public void setUserName(String input) {
        this.userName = input;  //TODO: check if username already taken
    }

    public void setName(String input) {
        name = input;
    }

    public void setLocation(String input) {
        location = input;
    }

    public void setFitnessLevel(String input) {
        fitnessLevel = "" + input;
    }

    private void setID() {
        int min = 11111111;
        int max = 99999999;
        this.userID = (int) Math.floor(Math.random() * (max - min + 1) + min) + this.email;
    }


    public void addFriend(String ID) {
        friendsList.add(ID);
    }

    public void removeFriend(int ID) {
        if(friendsList.contains(ID))
            friendsList.remove(friendsList.indexOf(ID));
    }

}