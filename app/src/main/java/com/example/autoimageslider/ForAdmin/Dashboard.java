package com.example.autoimageslider.ForAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.autoimageslider.R;
import com.example.autoimageslider.SignInPage;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView userMessageId = findViewById(R.id.userMessageId);
        userMessageId.setOnClickListener(v ->{
            Intent intent = new Intent(this, TripMessage.class);
            startActivity(intent);
        });

        CardView addPlacesID = findViewById(R.id.addPlacesID);
        addPlacesID.setOnClickListener(v ->{
            Intent intent = new Intent(this, AdminDashboard.class);
            startActivity(intent);
        });

        Button signOutForAdId = findViewById(R.id.signOutForAdId);
        signOutForAdId.setOnClickListener(v -> signOut());
    }
    private void signOut() {
        navigateToSignInPage();
        Toast.makeText(this, "Sign out successfully", Toast.LENGTH_SHORT).show();
    }

    private void navigateToSignInPage() {
        Intent intent = new Intent(this, SignInPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }
}