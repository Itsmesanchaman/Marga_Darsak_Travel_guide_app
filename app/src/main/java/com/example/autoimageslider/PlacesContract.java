package com.example.autoimageslider;

import android.net.Uri;

public class PlacesContract {

    public static final String AUTHORITY = "com.example.adminpanel";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_SPRING = "spring";
    public static final String PATH_SUMMER = "summer";
    public static final String PATH_AUTUMN = "autumn";
    public static final String PATH_WINTER = "winter";

    public static final Uri CONTENT_URI_SPRING = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SPRING).build();
    public static final Uri CONTENT_URI_SUMMER = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SUMMER).build();
    public static final Uri CONTENT_URI_AUTUMN = BASE_CONTENT_URI.buildUpon().appendPath(PATH_AUTUMN).build();
    public static final Uri CONTENT_URI_WINTER = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WINTER).build();

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
}
