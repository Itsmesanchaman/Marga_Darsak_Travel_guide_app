package com.example.autoimageslider.ForAdmin;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import java.util.Objects;

public class PlacesContentProvider extends ContentProvider {

    private static final String DATABASE_NAME = "my_places.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PLACES = "places";

    private SQLiteDatabase database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int SPRING = 100;
    public static final int SUMMER = 200;
    public static final int AUTUMN = 300;
    public static final int WINTER = 400;

    static {
        uriMatcher.addURI(PlacesContract.AUTHORITY, PlacesContract.PATH_SPRING, SPRING);
        uriMatcher.addURI(PlacesContract.AUTHORITY, PlacesContract.PATH_SUMMER, SUMMER);
        uriMatcher.addURI(PlacesContract.AUTHORITY, PlacesContract.PATH_AUTUMN, AUTUMN);
        uriMatcher.addURI(PlacesContract.AUTHORITY, PlacesContract.PATH_WINTER, WINTER);
    }

    @Override
    public boolean onCreate() {
        PlacesContentProvider.DatabaseHelper databaseHelper = new PlacesContentProvider.DatabaseHelper(getContext());
        database = databaseHelper.getWritableDatabase();
        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        int match = uriMatcher.match(uri);
        switch (match) {
            case SPRING:
            case SUMMER:
            case AUTUMN:
            case WINTER:
                cursor = database.query(TABLE_PLACES, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = uriMatcher.match(uri);
        long id = -1;

        switch (match) {
            case SPRING:
            case SUMMER:
            case AUTUMN:
            case WINTER:
                id = database.insert(TABLE_PLACES, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (id == -1) {
            throw new SQLException("Failed to insert row into " + uri);
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case SPRING:
            case SUMMER:
            case AUTUMN:
            case WINTER:
                rowsDeleted = database.delete(TABLE_PLACES, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (rowsDeleted != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case SPRING:
            case SUMMER:
            case AUTUMN:
            case WINTER:
                rowsUpdated = database.update(TABLE_PLACES, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (rowsUpdated != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableQuery = "CREATE TABLE " + TABLE_PLACES + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "description TEXT," +
                    "latitude REAL," +
                    "longitude REAL" +
                    ")";
            db.execSQL(createTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
