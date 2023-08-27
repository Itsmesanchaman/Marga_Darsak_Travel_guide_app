package com.example.autoimageslider;

import android.os.Parcel;
import android.os.Parcelable;

public class Destination implements Parcelable {
    private final String name;
    private final String address;
    private int imageResId;


    // Constructor, getters, setters, and other methods




    protected Destination(Parcel in) {
        name = in.readString();
        address = in.readString();
        int imageResId = in.readInt();
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public static final Creator<Destination> CREATOR = new Creator<Destination>() {
        @Override
        public Destination createFromParcel(Parcel in) {
            return new Destination(in);
        }

        @Override
        public Destination[] newArray(int size) {
            return new Destination[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
    }
}
