package com.example.autoimageslider;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public class PlaceDetailsActivity extends AppCompatActivity  {

    boolean isDetailsVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placedetail);

        Place selectedPlace = getIntent().getParcelableExtra("selectedPlace");


        ImageView backtoS = findViewById(R.id.backtoS);
        backtoS.setOnClickListener(v -> onBackPressed());

        Button viewmapId = findViewById(R.id.viewmapId);
        viewmapId.setOnClickListener(v -> {
            Intent intent = new Intent(PlaceDetailsActivity.this, ViewMap.class);
            startActivity(intent);
        });




        ImageSlider muk = findViewById(R.id.muk);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.mardi, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.mmhimal, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.mhim, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.mardi, ScaleTypes.FIT));

        muk.setImageList(slideModels, ScaleTypes.FIT);


        FrameLayout mapLayout = findViewById(R.id.mapLayout);
        ImageView imageView = findViewById(R.id.iconImageView1);
        ImageView imageView1 = findViewById(R.id.trekMapId);

        AppCompatButton planMyTripId = findViewById(R.id.planMyTripId);
        planMyTripId.setOnClickListener(v ->{
            Intent intent = new Intent(PlaceDetailsActivity.this, PlanMyTrip.class);
            startActivity(intent);
        });

        AppCompatButton tripCustomizeId = findViewById(R.id.tripCustomizeId);
        tripCustomizeId.setOnClickListener(v ->{
            Intent intent = new Intent(PlaceDetailsActivity.this, CustomizeTrip.class);
            startActivity(intent);
        });


        final boolean[] isMapVisible = {false};

        mapLayout.setOnClickListener(v ->{
            isMapVisible[0] = !isMapVisible[0];
            imageView1.setVisibility(isMapVisible[0] ? View.VISIBLE : View.GONE);
            imageView.setImageResource(isMapVisible[0] ? R.drawable.arrowup : R.drawable.down);
        });




        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        ImageView iconImageView = findViewById(R.id.iconImageView);

        frameLayout.setOnClickListener(v -> {
            isDetailsVisible = !isDetailsVisible;

            if (isDetailsVisible) {
                showDetails();
                iconImageView.setImageResource(R.drawable.arrowup);
            } else {
                hideDetails();
                iconImageView.setImageResource(R.drawable.down);
            }
        });
    }

    private void showDetails() {
        findViewById(R.id.oneId).setVisibility(View.VISIBLE);
        findViewById(R.id.day1TextView).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse1ImageView).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse2ImageView).setVisibility(View.VISIBLE);
        findViewById(R.id.recordButton1).setVisibility(View.VISIBLE);
        findViewById(R.id.textView2).setVisibility(View.VISIBLE);
        findViewById(R.id.AccId).setVisibility(View.VISIBLE);
        findViewById(R.id.txt3).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse3).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse4).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse5).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse15).setVisibility(View.VISIBLE);
        findViewById(R.id.imgh).setVisibility(View.VISIBLE);
        findViewById(R.id.gvhotel).setVisibility(View.VISIBLE);
        findViewById(R.id.AccIdL).setVisibility(View.VISIBLE);
        findViewById(R.id.txt3l).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse16).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse17).setVisibility(View.VISIBLE);
        findViewById(R.id.Numbertwo).setVisibility(View.VISIBLE);
        findViewById(R.id.textviewDay2).setVisibility(View.VISIBLE);
        findViewById(R.id.Numbertwoi).setVisibility(View.VISIBLE);
        findViewById(R.id.textviewDay2On).setVisibility(View.VISIBLE);
        findViewById(R.id.AccmmId).setVisibility(View.VISIBLE);
        findViewById(R.id.textviewDay2tea).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse18).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse19).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse20).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse21).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse7).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse8).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse9).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewday3).setVisibility(View.VISIBLE);
        findViewById(R.id.AccIdH).setVisibility(View.VISIBLE);
        findViewById(R.id.txt3H).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse10).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse11).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse22).setVisibility(View.VISIBLE);
        findViewById(R.id.NumbertwoW).setVisibility(View.VISIBLE);
        findViewById(R.id.textviewDay2W).setVisibility(View.VISIBLE);
        findViewById(R.id.AccmmIdw).setVisibility(View.VISIBLE);
        findViewById(R.id.textviewDay2teaw).setVisibility(View.VISIBLE);
        findViewById(R.id.NumberFour).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewday4).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse12).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse13).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse14).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse23).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse24).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse25).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse26).setVisibility(View.VISIBLE);
        findViewById(R.id.numberFive).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewday5).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse27).setVisibility(View.VISIBLE);
        findViewById(R.id.ellipse28).setVisibility(View.VISIBLE);
        findViewById(R.id.jeep).setVisibility(View.VISIBLE);
        findViewById(R.id.toPokhara).setVisibility(View.VISIBLE);
    }




    private void hideDetails() {
        findViewById(R.id.oneId).setVisibility(View.GONE);
        findViewById(R.id.day1TextView).setVisibility(View.GONE);
        findViewById(R.id.ellipse1ImageView).setVisibility(View.GONE);
        findViewById(R.id.ellipse2ImageView).setVisibility(View.GONE);
        findViewById(R.id.recordButton1).setVisibility(View.GONE);
        findViewById(R.id.textView2).setVisibility(View.GONE);
        findViewById(R.id.AccId).setVisibility(View.GONE);
        findViewById(R.id.txt3).setVisibility(View.GONE);
        findViewById(R.id.ellipse3).setVisibility(View.GONE);
        findViewById(R.id.ellipse4).setVisibility(View.GONE);
        findViewById(R.id.ellipse5).setVisibility(View.GONE);
        findViewById(R.id.ellipse15).setVisibility(View.GONE);
        findViewById(R.id.imgh).setVisibility(View.GONE);
        findViewById(R.id.gvhotel).setVisibility(View.GONE);
        findViewById(R.id.AccIdL).setVisibility(View.GONE);
        findViewById(R.id.txt3l).setVisibility(View.GONE);
        findViewById(R.id.ellipse16).setVisibility(View.GONE);
        findViewById(R.id.ellipse17).setVisibility(View.GONE);
        findViewById(R.id.Numbertwo).setVisibility(View.GONE);
        findViewById(R.id.textviewDay2).setVisibility(View.GONE);
        findViewById(R.id.Numbertwoi).setVisibility(View.GONE);
        findViewById(R.id.textviewDay2On).setVisibility(View.GONE);
        findViewById(R.id.AccmmId).setVisibility(View.GONE);
        findViewById(R.id.textviewDay2tea).setVisibility(View.GONE);
        findViewById(R.id.ellipse18).setVisibility(View.GONE);
        findViewById(R.id.ellipse19).setVisibility(View.GONE);
        findViewById(R.id.ellipse20).setVisibility(View.GONE);
        findViewById(R.id.ellipse21).setVisibility(View.GONE);
        findViewById(R.id.ellipse7).setVisibility(View.GONE);
        findViewById(R.id.ellipse8).setVisibility(View.GONE);
        findViewById(R.id.ellipse9).setVisibility(View.GONE);
        findViewById(R.id.textViewday3).setVisibility(View.GONE);
        findViewById(R.id.AccIdH).setVisibility(View.GONE);
        findViewById(R.id.txt3H).setVisibility(View.GONE);
        findViewById(R.id.ellipse10).setVisibility(View.GONE);
        findViewById(R.id.ellipse11).setVisibility(View.GONE);
        findViewById(R.id.ellipse22).setVisibility(View.GONE);
        findViewById(R.id.NumbertwoW).setVisibility(View.GONE);
        findViewById(R.id.textviewDay2W).setVisibility(View.GONE);
        findViewById(R.id.AccmmIdw).setVisibility(View.GONE);
        findViewById(R.id.textviewDay2teaw).setVisibility(View.GONE);
        findViewById(R.id.NumberFour).setVisibility(View.GONE);
        findViewById(R.id.textViewday4).setVisibility(View.GONE);
        findViewById(R.id.ellipse12).setVisibility(View.GONE);
        findViewById(R.id.ellipse13).setVisibility(View.GONE);
        findViewById(R.id.ellipse14).setVisibility(View.GONE);
        findViewById(R.id.ellipse23).setVisibility(View.GONE);
        findViewById(R.id.ellipse24).setVisibility(View.GONE);
        findViewById(R.id.ellipse25).setVisibility(View.GONE);
        findViewById(R.id.ellipse26).setVisibility(View.GONE);
        findViewById(R.id.numberFive).setVisibility(View.GONE);
        findViewById(R.id.textViewday5).setVisibility(View.GONE);
        findViewById(R.id.ellipse27).setVisibility(View.GONE);
        findViewById(R.id.ellipse28).setVisibility(View.GONE);
        findViewById(R.id.jeep).setVisibility(View.GONE);
        findViewById(R.id.toPokhara).setVisibility(View.GONE);
    }



}


