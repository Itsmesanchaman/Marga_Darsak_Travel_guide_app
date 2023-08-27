package com.example.autoimageslider;

public class Item {

    int placeListImage;
    String placeListName;

    public Item(String placeListName, int placeListImage) {
        this.placeListImage = placeListImage;
        this.placeListName = placeListName;
    }
    public int getPlaceListImage() {
        return placeListImage;
    }
    public String getPlaceListName() {
        return placeListName;
    }

}
