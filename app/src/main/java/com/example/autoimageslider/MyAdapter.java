package com.example.autoimageslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Item> {
    ArrayList<Item> placeList;

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        placeList = objects;
    }

    @Override
    public int getCount() {
        return placeList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_list, parent, false);
        }

        TextView textView = v.findViewById(R.id.placename);
        ImageView imageView = v.findViewById(R.id.placeimage);

        textView.setText(placeList.get(position).getPlaceListName());
        imageView.setImageResource(placeList.get(position).getPlaceListImage());

        return v;
    }


}
