package com.example.autoimageslider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class StepFour extends AppCompatActivity {

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_four);

        ImageView userPhoto = findViewById(R.id.userPhoto);
        TextView userNameShow = findViewById(R.id.userNameShow);
        AppCompatButton submitTOClassId = findViewById(R.id.submitTOClassId);
        AppCompatButton BackToId = findViewById(R.id.BackToId);
        BackToId.setOnClickListener(v ->onBackPressed());


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
        if (acc != null) {
            Uri photoUrl = acc.getPhotoUrl();
            if (photoUrl != null) {
                Glide.with(this)
                        .load(photoUrl)
                        .circleCrop()
                        .into(userPhoto);
            }
        }
        if (acc != null) {
            String googleUsername = acc.getDisplayName();
            userNameShow.setText(googleUsername);

        }

        submitTOClassId.setOnClickListener(v -> {

            assert acc != null;
            Uri userPhotoUrl = acc.getPhotoUrl();
            String username = acc.getDisplayName();

            sendUserDataBroadcast(userPhotoUrl, username);


            @SuppressLint("InflateParams") View toastView = getLayoutInflater().inflate(R.layout.custom_toast_layout, null);

            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastView);
            toast.show();


        });

    }
    private void sendUserDataBroadcast(Uri userPhotoUrl, String username) {
        Intent broadcastIntent = new Intent("user-data-event");
        broadcastIntent.putExtra("userPhotoUrl", userPhotoUrl.toString());
        broadcastIntent.putExtra("username", username);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }


}