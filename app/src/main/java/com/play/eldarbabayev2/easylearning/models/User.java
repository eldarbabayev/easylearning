package com.play.eldarbabayev2.easylearning.models;

import java.util.ArrayList;

public class User {

    private String userFullname;
    private String userDateOfBirth;
    private String userGender;
    private String userEmail;
    private String userCountry;

    private ArrayList<String> myGroups;


    public User() {
    }

    public User(String userFullname, String userDateOfBirth, String userGender, String userEmail, String userCountry, ArrayList<String> myGroups) {
        this.userFullname = userFullname;
        this.userDateOfBirth = userDateOfBirth;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userCountry = userCountry;
        this.myGroups = myGroups;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public void setMyGroups(ArrayList<String> myGroups) {
        this.myGroups = myGroups;
    }

    public ArrayList<String> getMyGroups() {

        return myGroups;
    }

}
