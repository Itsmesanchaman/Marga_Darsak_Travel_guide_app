package com.example.autoimageslider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;


public class SearchResultView extends AppCompatActivity {


    private ProgressBar progressBar;
    private EditText searchEditTextId;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresultview);


        PlacesClient placesClient = Places.createClient(this);

        searchEditTextId = findViewById(R.id.searchEditTextId);


        ImageView previousId = findViewById(R.id.previousId);
        previousId.setOnClickListener(v -> onBackPressed());


    }

}