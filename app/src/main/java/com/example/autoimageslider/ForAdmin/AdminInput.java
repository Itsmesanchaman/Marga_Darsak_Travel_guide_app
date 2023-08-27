package com.example.autoimageslider.ForAdmin;

public class AdminInput {
    private final String imageUrl;
    private final String title;

    public AdminInput(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}

