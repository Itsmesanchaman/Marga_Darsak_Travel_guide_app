package com.example.autoimageslider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_SELECTED_DATE = "selected_date";

    private static final String TABLE_INTERACTIONS = "interactions";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PROFILE_IMAGE = "profile_image";
    private static final String COLUMN_INTERACTION_ID = "interactionId";
    private static final String COLUMN_INTERACTION = "interaction";
    private static final String COLUMN_ADULT_COUNT = "adult_count";
    private static final String COLUMN_CHILD_COUNT = "child_count";
    public static final String COLUMN_VISIT_LOCATION = "visit_location";
    public static final String COLUMN_TRIP_SELECTION = "trip_selection";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PROFILE_IMAGE + " BLOB)";
        db.execSQL(createUserTableQuery);

        String createInteractionTableQuery = "CREATE TABLE " + TABLE_INTERACTIONS + "(" +
                COLUMN_INTERACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INTERACTION + " TEXT, " +
                COLUMN_VISIT_LOCATION + " TEXT, " +
                COLUMN_TRIP_SELECTION + " TEXT," +
                COLUMN_SELECTED_DATE + " TEXT)";
        db.execSQL(createInteractionTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_INTERACTIONS +
                    " ADD COLUMN " + COLUMN_VISIT_LOCATION + " TEXT");
            if (oldVersion < 1) {
                db.execSQL("ALTER TABLE " + TABLE_INTERACTIONS +
                        " ADD COLUMN " + COLUMN_SELECTED_DATE + " TEXT");
            }
        }
    }



    public void insertInteraction(String interaction, int adultCount, int childCount) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTERACTION, interaction);
        values.put(COLUMN_ADULT_COUNT, adultCount);
        values.put(COLUMN_CHILD_COUNT, childCount);
        db.insert(TABLE_INTERACTIONS, null, values);
        db.close();
    }
    public void insertInteraction(String interaction, String selectedDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTERACTION, interaction);
        values.put(COLUMN_SELECTED_DATE, selectedDate);
        db.insert(TABLE_INTERACTIONS, null, values);
        db.close();
    }

    public void insertInteraction(String interaction, String visitLocation, String tripSelection) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTERACTION, interaction);
        values.put(COLUMN_VISIT_LOCATION, visitLocation);
        values.put(COLUMN_TRIP_SELECTION, tripSelection);
        db.insert(TABLE_INTERACTIONS, null, values);
        db.close();
    }




}



