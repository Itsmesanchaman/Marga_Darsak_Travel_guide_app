package com.example.autoimageslider;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Profile extends AppCompatActivity {

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        ImageView imageView = findViewById(R.id.backtoPfId);
        imageView.setOnClickListener(v ->onBackPressed());

        ImageView userProfileId = findViewById(R.id.userProfileId);
        TextView usernameId = findViewById(R.id.usernameId);
        TextView emailAddressId = findViewById(R.id.emailAddressId);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(Profile.this,gso);

        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(Profile.this);
        if (acc != null) {
            Uri photoUrl = acc.getPhotoUrl();
            if (photoUrl != null) {
                Glide.with(Profile.this)
                        .load(photoUrl)
                        .circleCrop()
                        .into(userProfileId);
            }
        }
        if (acc != null) {
            String googleUsername = acc.getDisplayName();
            String email = acc.getEmail();

            usernameId.setText(googleUsername);
            emailAddressId.setText(email);

        }

    }

}