package com.example.autoimageslider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    GridView placeList;

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;
    private SharedPreferences sharedPreferences;
    ArrayList<Item> item_list = new ArrayList<>();
    private static final int GALLERY_REQUEST_CODE = 1001;
    private ImageView imgBTN;
    private DbHelper sqliteHelper;
    private static final String IMAGE_URI_KEY = "image_uri";
    private boolean isGoogleSignIn = false;
    private TextView userAcId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CardView adBtn = view.findViewById(R.id.adBtn);
        adBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getContext(), Adventure.class);
            startActivity(intent);
        });

        CardView pndBtn = view.findViewById(R.id.pndBtn);
        pndBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getContext(), PopularDestination.class);
            startActivity(intent);
        });

        CardView resBtn = view.findViewById(R.id.resBtn);
        resBtn.setOnClickListener(v -> {
           Intent intent = new Intent(requireActivity(), PlanMyTrip.class);
           startActivity(intent);
        });


        userAcId = view.findViewById(R.id.userAcId);
        imgBTN = view.findViewById(R.id.imgBTN);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(requireActivity(),gso);

        sqliteHelper = new DbHelper(requireContext());

       GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(requireActivity());
       if(acc != null){
           isGoogleSignIn = true;

           String personName = acc.getDisplayName();
           Uri photoUrl = acc.getPhotoUrl();

           userAcId.setText(personName);

           if(photoUrl != null){
               Glide.with(requireContext())

                       .load(photoUrl)
                       .circleCrop()
                       .into(imgBTN);
           }
       }else {
           isGoogleSignIn = false;

           String username = "user123";
           String password = "password";
           loginUser(username, password);

           imgBTN.setOnClickListener(v -> {
               openGallery();
           });
       }




        placeList = view.findViewById(R.id.placeList);

        item_list.add(new Item("Spring", R.drawable.spring));
        item_list.add(new Item("Summer", R.drawable.summer));
        item_list.add(new Item("Autumn", R.drawable.autumn));
        item_list.add(new Item("Winter", R.drawable.winter));

        MyAdapter myAdapter = new MyAdapter(requireActivity(), R.layout.item_list, item_list);
        placeList.setAdapter(myAdapter);

        placeList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(requireActivity(), SeasonActivity.class);
            intent.putExtra(SeasonActivity.EXTRA_SEASON, position);
            startActivity(intent);
        });


        sharedPreferences = requireActivity().getSharedPreferences("accountPrefs", Context.MODE_PRIVATE);

        String savedImageUri = sharedPreferences.getString(IMAGE_URI_KEY, null);
        if (savedImageUri != null) {
            Uri imageUri = Uri.parse(savedImageUri);
            loadImageFromUri(imageUri);
        }

        imgBTN.setOnClickListener(v -> showImageOptionsDialog());


        return view;
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                sharedPreferences.edit().putString(IMAGE_URI_KEY, imageUri.toString()).apply();

                loadImageFromUri(imageUri);
            }
        }
    }


    private void showImageOptionsDialog() {
        if (isGoogleSignIn()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        if (isImageSet()) {
            builder.setItems(new CharSequence[]{"Upload Photo", "Remove Photo"}, (dialog, which) -> {
                if (which == 0) {
                    openGallery();
                } else if (which == 1) {
                    removePhoto();
                }
            });
        } else {
            builder.setItems(new CharSequence[]{"Upload Photo"}, (dialog, which) -> {
                if (which == 0) {
                    openGallery();
                }
            });
        }

        builder.show();
    }



    private void removePhoto() {
        sharedPreferences.edit().remove(IMAGE_URI_KEY).apply();

        Glide.with(requireContext())
                .load(R.drawable.users)
                .circleCrop()
                .into(imgBTN);
    }
    private void loadImageFromUri(Uri imageUri) {
        if (isGoogleSignIn()) {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireActivity());
            if (account != null) {
                Uri photoUrl = account.getPhotoUrl();
                if (photoUrl != null) {
                    Glide.with(requireContext())
                            .load(photoUrl)
                            .circleCrop()
                            .into(imgBTN);
                }
            }
        } else {
            Glide.with(requireContext())
                    .load(imageUri)
                    .circleCrop()
                    .into(imgBTN);
        }
    }

    private boolean isImageSet() {
        String savedImageUri = sharedPreferences.getString(IMAGE_URI_KEY, null);
        return savedImageUri != null;
    }
    private boolean isGoogleSignIn() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireActivity());
        return account != null;
    }
    private void loginUser(String username, String password) {
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor != null && cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex("username");
            String loggedInUsername = cursor.getString(usernameIndex);

            userAcId.setText(loggedInUsername);

            cursor.close();
        }

        db.close();
    }


}



