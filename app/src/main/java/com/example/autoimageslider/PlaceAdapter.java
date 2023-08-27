package com.example.autoimageslider;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends ArrayAdapter<Place> {


    public PlaceAdapter(Context context, List<Place> places) {
        super(context, 0, places);
        SparseBooleanArray selectedItems = new SparseBooleanArray();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.placelist, parent, false);
        }

        Place place = getItem(position);

        ImageView placeImage = convertView.findViewById(R.id.placeListId);
        TextView placeName = convertView.findViewById(R.id.placeName);


        placeImage.setImageResource(place.getImageResId());
        placeName.setText(place.getName());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PlaceDetailsActivity.class);
            intent.putExtra("selectedPlace", place);
            getContext().startActivity(intent);
        });


        return convertView;
    }

}
