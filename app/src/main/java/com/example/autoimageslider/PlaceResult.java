package com.example.autoimageslider;

public class PlaceResult {
    private final String placeName;
    private final String imageUrl;

    public PlaceResult(String placeName, String imageUrl) {
        this.placeName = placeName;
        this.imageUrl = imageUrl;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

