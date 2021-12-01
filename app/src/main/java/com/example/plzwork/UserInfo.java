package com.example.plzwork;

import android.view.View;
import android.widget.*;

import androidx.recyclerview.widget.*;

import com.google.firebase.database.Query;

class UserInfo {
    String userName;
    String name;
    String location;
    String email;
    String phoneNumber;
    String fitnessLevel;
    int userID;
    int[] friendsList;
    int end;
    int capacity;

    public UserInfo() {
        userName = "";
        name = "";
        location = "";
        email = "";
        phoneNumber = "";
        fitnessLevel = "";
        userID = 0;
        end = 0;
        capacity = 0;
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

    public int GetUserID() {
        return userID;
    }

    public int[] GetFriendsList() {
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
        //check if entered username is unique
        //if unique set userName to input
        //else return an error
    }

    public void SetName(String input) {
        name = input;
    }

    public void SetLocation(String input) {
        location = input;
    }

    public void SetFitnessLevel(int input) {
        fitnessLevel = "" + input;
    }

    private int SetID() {
        int min = 11111111;
        int max = 99999999;
        boolean unique = false;

        while (unique == false) {
            int ID = (int) Math.floor(Math.random() * (max - min + 1) + min);
            if (CheckUser(ID) == false) {
                return ID;
            }
        }
        return 0;
    }

    private boolean CheckUser(int ID) {
        //TODO Check if an account exists with the passed ID
        return true;
    }

    public void AddFriend(int ID) {
        if (CheckUser(ID)) {
            if (end == capacity) {
                IncreaseSize();
            }

            friendsList[end] = ID;
            end++;
        } else {
            //print error message
        }
        //Might have this function return a boolean
        //Doing so will allow us to print the error message out in main onto screen
    }

    public void RemoveFriend(int ID) {
        boolean found = false;
        int index = 0;

        for (int i = 0; i < friendsList.length; i++) {
            if (friendsList[i] == ID) {
                found = true;
                index = i;
            }
        }

        if (found) {
            end = end - 1;
            for (int i = index; i < friendsList.length - 1; i++) {
                friendsList[i] = friendsList[i + 1];
            }
        }
    }

    private void IncreaseSize() {
        if (friendsList.length == 0) {
            int[] newList = {0};
            capacity = 1;
        } else {
            int newSize = friendsList.length * 2;
            int[] newList = new int[newSize];
            capacity = newSize;

            for (int i = 0; i < friendsList.length; i++) {
                newList[i] = friendsList[1];
            }

            friendsList = newList;
        }
    }


}