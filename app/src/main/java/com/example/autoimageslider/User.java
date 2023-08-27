package com.example.autoimageslider;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private final byte[] profileImage;

    public User(String username, String password, String email, byte[] profileImage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }
}

