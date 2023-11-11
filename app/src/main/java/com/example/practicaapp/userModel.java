package com.example.practicaapp;

public class userModel {

    private String userKey;
    private String userName;
    private String userEmail;
    private String userPass;

    private String userGenero;

    public String getUserGenero() {
        return userGenero;
    }

    public void setUserGenero(String userGenero) {
        this.userGenero = userGenero;
    }

    public userModel(String userName, String userEmail, String userPass, String userGenero) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userGenero = userGenero;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public userModel(String userEmail, String userPass) {
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public userModel(String userKey) {
        this.userKey = userKey;
    }

    public userModel(String userName, String userEmail, String userPass) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public userModel() {
    }
}
