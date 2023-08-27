package com.example.autoimageslider.PlaceDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.autoimageslider.R;
import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;

public class Annapurna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annapurna);

        Place selectedPlace = getIntent().getParcelableExtra("selectedPlace");


        ImageView backtoAnn = findViewById(R.id.backtoAnn);
        backtoAnn.setOnClickListener(v -> onBackPressed());

        ImageSlider annapurnaId = findViewById(R.id.annapurnaId);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.annapurna, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.basecamp, ScaleTypes.FIT));


        annapurnaId.setImageList(slideModels, ScaleTypes.FIT);

        ImageView iconImage = findViewById(R.id.iconImage);
        ImageView mapTrekId = findViewById(R.id.mapTrekId);
        FrameLayout trekMapLayout = findViewById(R.id.trekMapLayout);

        final boolean[] isMapVisible = {false};

        trekMapLayout.setOnClickListener(v ->{
            isMapVisible[0] = !isMapVisible[0];
            mapTrekId.setVisibility(isMapVisible[0] ? View.VISIBLE : View.GONE);
            iconImage.setImageResource(isMapVisible[0] ? R.drawable.arrowup : R.drawable.down);
        });
    }
}