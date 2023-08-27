package com.example.autoimageslider;

import java.io.Serializable;

public class Place implements Serializable {

    private final int imageResId;
    private final String name;

    private final int id;


    public Place(int imageResId, String name, int id) {
        this.imageResId = imageResId;
        this.name = name;
        this.id = id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public int getId(){return id;}

}

