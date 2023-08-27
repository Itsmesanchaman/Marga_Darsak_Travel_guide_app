package com.example.autoimageslider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Plan extends Fragment {

    private TextView textPlan;
    private TextView textFavorites;
    private View indicator;
    private float startPosition;
    private List<String> planItems;
    private List<String> favoritesItems;
    private LinearLayout favoritesContainer;

    private TextView planMessageTextView;
    private TextView favoritesMessageTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        textPlan = view.findViewById(R.id.textPlan);
        TextView textFavorites = view.findViewById(R.id.textFavorites);
        indicator = view.findViewById(R.id.indicator);
        favoritesContainer = view.findViewById(R.id.favoritesContainer);

        planMessageTextView = view.findViewById(R.id.planMessageTextView);
        favoritesMessageTextView = view.findViewById(R.id.favoritesMessageTextView);

        textPlan.setOnClickListener(v -> {
            showPlanMessage();
            moveIndicatorToText(textPlan);
        });

        textFavorites.setOnClickListener(v -> {
            showFavoritesMessage();
            moveIndicatorToText(textFavorites);
        });


        indicator.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startPosition = event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float distance = event.getRawX() - startPosition;
                    float newPosition = indicator.getX() + distance;
                    updateIndicatorPosition(newPosition);
                    startPosition = event.getRawX();
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    break;
            }
            return true;
        });

        planItems = new ArrayList<>();
        favoritesItems = new ArrayList<>();

        return view;
    }

    private void updateIndicatorPosition(float xPosition) {
        // Limit the indicator's movement between the center of textPlan and textFavorites
        float minX = textPlan.getX() + textPlan.getWidth() / 2f - indicator.getWidth() / 2f;
        float maxX = textFavorites.getX() + textFavorites.getWidth() / 2f - indicator.getWidth() / 2f;
        float newPosition = Math.max(minX, Math.min(xPosition, maxX));
        indicator.setX(newPosition);
    }
    private void moveIndicatorToText(TextView textView) {
        float newX = textView.getX() + textView.getWidth() / 2f - indicator.getWidth() / 2f;
        indicator.animate().x(newX).setDuration(200).start();
    }


    private void showPlanMessage() {
        favoritesMessageTextView.setVisibility(View.GONE);
        planMessageTextView.setVisibility(View.VISIBLE);
    }
    private void showFavoritesMessage() {
        planMessageTextView.setVisibility(View.GONE);
        favoritesMessageTextView.setVisibility(View.VISIBLE);
    }


}
