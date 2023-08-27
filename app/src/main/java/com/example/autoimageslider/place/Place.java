package com.example.autoimageslider.place;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Place implements Serializable {
    private final LatLng location;
    private final int imageResourceId;
    private final String title;

    public Place(LatLng location, int imageResourceId, String title) {
        this.location = location;
        this.imageResourceId = imageResourceId;
        this.title = title;
    }

    public LatLng getLocation() {
        return location;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    public String getTitle() {
        return title;
    }
}

