package com.example.autoimageslider;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class Search extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        EditText searchText = view.findViewById(R.id.searchText);
        searchText.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SearchResultView.class);
            startActivity(intent);
        });

        return view;
    }
}




