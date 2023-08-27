package com.example.autoimageslider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class SeasonActivity extends AppCompatActivity {

    public static final String EXTRA_SEASON = "extra_season";

    public static final int SPRING = 0;
    public static final int SUMMER = 1;
    public static final int AUTUMN = 2;
    public static final int WINTER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);

        int season = getIntent().getIntExtra(EXTRA_SEASON, SPRING);
        inflateSeasonLayout(season);

        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> onBackPressed());
    }

    private void inflateSeasonLayout(int season) {
        View seasonLayout;

        switch (season) {
            case SPRING:
                seasonLayout = getLayoutInflater().inflate(R.layout.spring_layout, findViewById(R.id.springPlaceList), false);
                GridView springGridView = seasonLayout.findViewById(R.id.springPlaceList);
                ArrayList<Place> springPlaces = getSpringPlaces();
                PlaceAdapter springAdapter = new PlaceAdapter(this, springPlaces);

                springGridView.setAdapter(springAdapter);


                HashMap<Integer, Class<?>> placeActivityMap = new HashMap<>();
                placeActivityMap.put(1, PlaceDetailsActivity.class);
                placeActivityMap.put(2, PlanMyTrip.class);

                springGridView.setOnItemClickListener((adapterView, view, position, id) -> {
                    Place selectedPlace = springPlaces.get(position);
                    int placeId = selectedPlace.getId();

                    Class<?> destinationActivity = placeActivityMap.get(placeId);
                    if (destinationActivity != null) {
                        Intent intent = new Intent(SeasonActivity.this, destinationActivity);
                        intent.putExtra("selectedPlace", selectedPlace);
                        startActivity(intent);
                    }

                    Log.d("DEBUG", "placeId: " + placeId);
                    Log.d("DEBUG", "destinationActivity: " + destinationActivity);
                });




                break;
            case SUMMER:
                seasonLayout = getLayoutInflater().inflate(R.layout.summer_layout, findViewById(R.id.summerPlaceList), false);
                GridView summerGridView = seasonLayout.findViewById(R.id.summerPlaceList);
                ArrayList<Place> summerPlaces = getSummerPlaces();
                PlaceAdapter summerAdapter = new PlaceAdapter(this, summerPlaces);
                summerGridView.setAdapter(summerAdapter);
                break;
            case AUTUMN:
                seasonLayout = getLayoutInflater().inflate(R.layout.autumn_layout, findViewById(R.id.autumnPlaceList), false);
                GridView autumnGridView = seasonLayout.findViewById(R.id.autumnPlaceList);
                ArrayList<Place> autumnPlaces = getAutumnPlaces();
                PlaceAdapter autumnAdapter = new PlaceAdapter(this, autumnPlaces);
                autumnGridView.setAdapter(autumnAdapter);
                break;
            case WINTER:
                seasonLayout = getLayoutInflater().inflate(R.layout.winter_layout, findViewById(R.id.winterPlaceList), false);
                GridView winterGridView = seasonLayout.findViewById(R.id.winterPlaceList);
                ArrayList<Place> winterPlaces = getWinterPlaces();
                PlaceAdapter winterAdapter = new PlaceAdapter(this, winterPlaces);
                winterGridView.setAdapter(winterAdapter);
                break;
            default:
                seasonLayout = getLayoutInflater().inflate(R.layout.spring_layout, findViewById(R.id.springPlaceList), false);
                break;
        }

        FrameLayout container = findViewById(R.id.seasonContainer);
        container.removeAllViews();
        container.addView(seasonLayout);
    }


    private ArrayList<Place> getSpringPlaces() {
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.drawable.mch,"Mardi Himal",1));
        places.add(new Place(R.drawable.annapurna, "Annapurna",2));
        return places;
    }

    private ArrayList<Place> getSummerPlaces() {
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.drawable.pkhr, "Pokhara",3));
        places.add(new Place(R.drawable.ilam, "Ilam",4));
        return places;
    }

    private ArrayList<Place> getAutumnPlaces() {
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.drawable.rara, "Rara Lake",5));
        places.add(new Place(R.drawable.muk, "Muktinath Temple",6));
        return places;
    }

    private ArrayList<Place> getWinterPlaces() {
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.drawable.kln, "Kalinchowk",7));
        places.add(new Place(R.drawable.ctwn, "Chitwan National Park",8));
        return places;
    }


}
