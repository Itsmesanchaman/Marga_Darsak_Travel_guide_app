package com.example.autoimageslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;


import java.util.ArrayList;


public class PopularDestination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_destination);

        ImageView backToM = findViewById(R.id.backToM);
        backToM.setOnClickListener(v -> onBackPressed());

        RecyclerView recyclerView = findViewById(R.id.popId);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Annapurna Circuit", R.drawable.cir));
        items.add(new Item("Namche Bazaar", R.drawable.nmch));
        items.add(new Item("Pokhara", R.drawable.pkr));
        items.add(new Item("Kathmandu", R.drawable.ktm));
        items.add(new Item("Chitwan National Park", R.drawable.ctwn));
        items.add(new Item("Patan", R.drawable.ptn));
        items.add(new Item("Swayambhunath", R.drawable.swym));
        items.add(new Item("Bhaktapur", R.drawable.pur));
        items.add(new Item("Nagarkot", R.drawable.ngr));

        PopAdapter myAdapter = new PopAdapter(this, items);
        recyclerView.setAdapter(myAdapter);
        }

    }

