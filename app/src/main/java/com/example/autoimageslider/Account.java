package com.example.autoimageslider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class Account extends Fragment {

    GoogleSignInOptions gso;
    boolean isGoogleSignIn;
    GoogleSignInClient gsc;
    private SharedPreferences sharedPreferences;
    private static final String IMAGE_URI_KEY = "image_uri";
    private ImageView accountImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        accountImg = rootView.findViewById(R.id.accountImg);
        Button signOutId = rootView.findViewById(R.id.signOutId);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(requireContext(), gso);

        sharedPreferences = requireActivity().getSharedPreferences("accountPrefs", Context.MODE_PRIVATE);

        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(requireActivity());
        if (acc != null) {
            isGoogleSignIn = true;
            Uri photoUrl = acc.getPhotoUrl();
            if (photoUrl != null) {
                Glide.with(requireContext())
                        .load(photoUrl)
                        .circleCrop()
                        .into(accountImg);
            }
        } else {
            isGoogleSignIn = false;
            String savedImageUri = sharedPreferences.getString(IMAGE_URI_KEY, null);
            if (savedImageUri != null) {
                Uri imageUri = Uri.parse(savedImageUri);
                loadImageFromUri(imageUri);
            } else {
                Glide.with(requireContext())
                        .load(R.drawable.users)
                        .circleCrop()
                        .into(accountImg);
            }
        }

        if (isGoogleSignIn) {
            signOutId.setOnClickListener(v -> signOutGoogle());
        } else {
            signOutId.setOnClickListener(v -> signOut());
        }

        AppCompatButton acountProfileId = rootView.findViewById(R.id.acountProfileId);
        acountProfileId.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), Profile.class);
            startActivity(intent);
        });

        return rootView;
    }

    private void signOutGoogle() {
        gsc.signOut().addOnCompleteListener(task -> {
            navigateToSignInPage();
            Toast.makeText(requireContext(), "Sign out successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void signOut() {
        navigateToSignInPage();
        Toast.makeText(requireContext(), "Sign out successfully", Toast.LENGTH_SHORT).show();
    }

    private void navigateToSignInPage() {
        Intent intent = new Intent(requireContext(), SignInPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    private void loadImageFromUri(Uri imageUri) {
        Glide.with(requireContext())
                .load(imageUri)
                .circleCrop()
                .into(accountImg);
    }
}

