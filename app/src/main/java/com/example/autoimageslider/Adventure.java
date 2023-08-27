package com.example.autoimageslider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class Adventure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        ImageView backToH = findViewById(R.id.backToH);
        backToH.setOnClickListener(v -> onBackPressed());

        RecyclerView recyclerView1 = findViewById(R.id.Adlist);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerView recyclerView2 = findViewById(R.id.rafId);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerView recyclerView3 = findViewById(R.id.bungeeId);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Item("Paragliding in Pokhara", R.drawable.para));
        items1.add(new Item("Paragliding in Bandipur", R.drawable.bandi));

        ArrayList<Item> items2 = new ArrayList<>();
        items2.add(new Item("White-water rafting in Trisuli River", R.drawable.tris));
        items2.add(new Item("White-water rafting in Bhote Kosi River", R.drawable.bhote));
        items2.add(new Item("White-water rafting in Upper Seti River", R.drawable.upseti));
        items2.add(new Item("White-water rafting in Upper Seti River", R.drawable.upseti));
        items2.add(new Item("White-water rafting in Upper Seti River", R.drawable.upseti));


        ArrayList<Item> items3 = new ArrayList<>();
        items3.add(new Item("Hemja, Pokhara", R.drawable.pokbung));
        items3.add(new Item("Kushma Bungee", R.drawable.kusmabung));


        PopAdapter adapter1 = new PopAdapter(this, items1);
        recyclerView1.setAdapter(adapter1);

        PopAdapter adapter2 = new PopAdapter(this, items2);
        recyclerView2.setAdapter(adapter2);

        PopAdapter adapter3 = new PopAdapter(this, items3);
        recyclerView3.setAdapter(adapter3);

    }
}
