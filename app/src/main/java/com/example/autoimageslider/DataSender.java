package com.example.autoimageslider;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DataSender {
    public static final String NEW_DATA_ACTION = "com.example.adminpanel.NEW_DATA_ACTION";


    public static void sendData(Context context, String interactionData, String visitLocationData, String tripSelectionData) {
        Log.d("DataSender", "Sending broadcast");
        Intent dataIntent = new Intent(NEW_DATA_ACTION);
        dataIntent.putExtra("interaction", interactionData);
        dataIntent.putExtra("visitLocation", visitLocationData);
        dataIntent.putExtra("tripSelection", tripSelectionData);
        context.sendBroadcast(dataIntent);
    }
    public static void sendDataWithoutTripSelection(Context context, String interactionData, String selectedDateData) {
        Intent dataIntent = new Intent(NEW_DATA_ACTION);
        dataIntent.putExtra("interaction", interactionData);
        dataIntent.putExtra("selectedDate", selectedDateData);
        context.sendBroadcast(dataIntent);
    }
    public static void sendSimpleData(Context context, String message) {
        Intent dataIntent = new Intent(NEW_DATA_ACTION);
        dataIntent.putExtra("message", message);
        context.sendBroadcast(dataIntent);
    }

}
