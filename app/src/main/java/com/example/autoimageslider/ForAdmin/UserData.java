package com.example.autoimageslider.ForAdmin;

public class UserData {
    private final String username;
    private final String userPhotoUrl;

    public UserData(String username, String userPhotoUrl) {
        this.username = username;
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }
}

