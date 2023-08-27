package com.example.autoimageslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Item> items;

    public PopAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.placelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.placeImage.setImageResource(item.getPlaceListImage());
        holder.placeName.setText(item.getPlaceListName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Item> newItems) {
        items.addAll(newItems);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImage;
        TextView placeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.placeListId);
            placeName = itemView.findViewById(R.id.placeName);
        }
    }
}
