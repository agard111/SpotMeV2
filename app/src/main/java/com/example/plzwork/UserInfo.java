package com.example.plzwork;

import java.util.*;

class UserInfo {
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

    public String GetName() {
        return name;
    }

    public String GetLocation() {
        return location;
    }

    public String GetFitnessLevel() {
        return fitnessLevel;
    }

    public String GetUserID() {
        return userID;
    }

    public ArrayList<String> GetFriendsList() {
        return friendsList;
    }

    public String GetUserName() {
        return userName;
    }

    public String GetPhoneNumber() {
        return phoneNumber;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String input) {
        email = input;
        // TODO update this to verify that a valid email is used
        // Maybe take the email used for Google Auth
    }

    public void SetPhoneNumber(String input) {
        phoneNumber = input;
    }

    public void SetUserName(String input) {
        this.userName = input;  //TODO: check if username already taken
    }

    public void SetName(String input) {
        name = input;
    }

    public void SetLocation(String input) {
        location = input;
    }

    public void SetFitnessLevel(String input) {
        fitnessLevel = "" + input;
    }

    private void SetID() {
        int min = 11111111;
        int max = 99999999;
        this.userID = (int) Math.floor(Math.random() * (max - min + 1) + min) + this.email;
    }


    public void AddFriend(String ID) {
        friendsList.add(ID);
    }

    public void RemoveFriend(int ID) {
        if(friendsList.contains(ID))
            friendsList.remove(friendsList.indexOf(ID));
    }

}