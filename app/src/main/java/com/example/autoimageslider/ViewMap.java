package com.example.autoimageslider;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.autoimageslider.place.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class ViewMap extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;

    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);

        ImageView previousToId = findViewById(R.id.previousToId);
        previousToId.setOnClickListener(v -> onBackPressed());

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.getUiSettings().setZoomControlsEnabled(true);

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
            return;
        }
        map.setMyLocationEnabled(true);

        listPoints = new ArrayList<>();

        List<Place> places = new ArrayList<>();
        places.add(new Place(new LatLng(28.26689, 83.96851), R.drawable.none, "Pokhara"));
        places.add(new Place(new LatLng(28.2946611, 83.8230963), R.drawable.trekking, "Kande"));
        places.add(new Place(new LatLng(28.32992, 83.83003), R.drawable.ntwo, "Pittam Deurali"));
        places.add(new Place(new LatLng(28.40316, 83.85704), R.drawable.nthree, "Mardi Himal Low Camp"));
        places.add(new Place(new LatLng(28.43349, 83.86776), R.drawable.nfour, "Mardi Himal High Camp"));
        places.add(new Place(new LatLng(28.46429, 83.90168), R.drawable.trekking, "Mardi Himal Base Camp"));
        places.add(new Place(new LatLng(28.40316, 83.85704), R.drawable.nthree, "Mardi Himal Low Camp"));
        places.add(new Place(new LatLng(28.38481, 83.87472), R.drawable.nfive, "Sidhing"));
        places.add(new Place(new LatLng(28.26689, 83.96851), R.drawable.none, "Pokhara"));


/*
        places.add(new Place(new LatLng(27.69184147572364, 85.29231975327816), R.drawable.jana, "Janamaitri Multiple Campus"));
*/






        for (Place place : places) {
            LatLng location = place.getLocation();
            String title = place.getTitle();
            int imageResourceId = place.getImageResourceId();


            MarkerOptions markerOptions = new MarkerOptions()
                    .position(location)
                    .title(title);

            Bitmap originalMarkerBitmap = BitmapFactory.decodeResource(getResources(), imageResourceId);

            int desiredWidth = 80;
            int desiredHeight = 80;

            Bitmap scaledMarkerBitmap = Bitmap.createScaledBitmap(originalMarkerBitmap, desiredWidth, desiredHeight, false);

            BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromBitmap(scaledMarkerBitmap);

            markerOptions.icon(markerIcon);


            Marker marker = map.addMarker(markerOptions);
            assert marker != null;
            marker.setTag(place);
        }

        map.setOnMarkerClickListener(marker -> {
            Place place = (Place) marker.getTag();
            if (place != null) {

                Toast.makeText(this, place.getTitle(), Toast.LENGTH_SHORT).show();
            }
            return false;
        });

        float zoomLevel = 12.0f;
        if (!places.isEmpty()) {
            LatLng firstLocation = places.get(0).getLocation();
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, zoomLevel));
        }



        PolylineOptions polylineOptions = new PolylineOptions();
        for (Place place : places) {
            LatLng location = place.getLocation();
            polylineOptions.add(location);
        }
        polylineOptions.color(Color.BLUE);
        polylineOptions.width(10);
        map.addPolyline(polylineOptions);

    }


    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
