package com.example.autoimageslider.ForAdmin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.example.autoimageslider.R;


public class TripMessage extends AppCompatActivity {

    private TextView userUsername;
    private ImageView userImageView;

    private TextView interactionTextView;

    private TextView visitLocationTextView;
    private TextView tripSelectionTextView;
    private final BroadcastReceiver userDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String userPhotoUrl = intent.getStringExtra("userPhotoUrl");
            String username = intent.getStringExtra("username");

            Glide.with(TripMessage.this)
                    .load(Uri.parse(userPhotoUrl))
                    .circleCrop()
                    .into(userImageView);

            userUsername.setText(username);
        }
    };

    private final BroadcastReceiver planMyTripDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String interaction = intent.getStringExtra("interaction");

            interactionTextView.setText(interaction);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_message);

        userUsername = findViewById(R.id.userUsername);
        userImageView = findViewById(R.id.userImageView);
        interactionTextView = findViewById(R.id.interactionId);

        visitLocationTextView = findViewById(R.id.visitLocationId);
        tripSelectionTextView = findViewById(R.id.tripSelectionId);

        LocalBroadcastManager.getInstance(this).registerReceiver(userDataReceiver, new IntentFilter("user-data-event"));

        LocalBroadcastManager.getInstance(this).registerReceiver(planMyTripDataReceiver, new IntentFilter("plan-my-trip-data-event"));


        ImageView backToDashboardId = findViewById(R.id.backToDashboardId);
        backToDashboardId.setOnClickListener(v -> onBackPressed());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(userDataReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(planMyTripDataReceiver);

    }

}
