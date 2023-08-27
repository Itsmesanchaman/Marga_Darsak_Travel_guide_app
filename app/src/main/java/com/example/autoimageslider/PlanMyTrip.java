package com.example.autoimageslider;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PlanMyTrip extends AppCompatActivity {

    public PlanMyTrip() {
    }

    private String interaction;
    private int adultCount;
    private int childCount;
    private AppCompatButton travellingSoloButton;
    private AppCompatButton travellingCoupleButton;
    private AppCompatButton travellingFamilyButton;
    private AppCompatButton travellingGroupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_my_trip);

        AppCompatButton continueButton = findViewById(R.id.continueId);
         travellingSoloButton = findViewById(R.id.travellingSoloId);
         travellingCoupleButton = findViewById(R.id.travellingCoupleId);
         travellingFamilyButton = findViewById(R.id.travellingFamilyId);
         travellingGroupButton = findViewById(R.id.travellingGroupId);
        ImageView cancelToId = findViewById(R.id.cancelToId);

        cancelToId.setOnClickListener(v -> onBackPressed());



        setButtonClickListener(travellingSoloButton);
        setButtonClickListener(travellingCoupleButton);
        setButtonClickListener(travellingFamilyButton);
        setButtonClickListener(travellingGroupButton);



        continueButton.setOnClickListener(v -> {
            boolean isAnyButtonSelected = travellingSoloButton.isSelected() ||
                    travellingCoupleButton.isSelected() ||
                    travellingFamilyButton.isSelected() ||
                    travellingGroupButton.isSelected();

            if (isAnyButtonSelected) {
                if (travellingFamilyButton.isSelected() && getAdultCount() <= 0) {
                    Toast.makeText(getApplicationContext(), "Number of adults must be greater than 0", Toast.LENGTH_SHORT).show();
                } else {

                    if (travellingSoloButton.isSelected()) {
                        interaction = travellingSoloButton.getText().toString();
                        adultCount = 0;
                        childCount = 0;
                    } else if (travellingCoupleButton.isSelected()) {
                        interaction = travellingCoupleButton.getText().toString();
                        adultCount = 0;
                        childCount = 0;
                    } else if (travellingFamilyButton.isSelected()) {
                        interaction = travellingFamilyButton.getText().toString();
                        adultCount = getAdultCount();
                        childCount = getChildCount();
                    } else if (travellingGroupButton.isSelected()) {
                        interaction = travellingGroupButton.getText().toString();
                        adultCount = getAdultCount();
                        childCount = getChildCount();
                    }



                    String selectedTravelType = getSelectedTravelType();

                    interaction = "Travelling type: " + selectedTravelType;

                    storeUserInteraction(interaction, 0, 0);

                    Intent tripMessageIntent = new Intent("plan-my-trip-data-event");
                    tripMessageIntent.putExtra("interaction", interaction);
                    sendBroadcast(tripMessageIntent);



                    Intent stepTwoIntent = new Intent(PlanMyTrip.this, StepTwo.class);
                    startActivity(stepTwoIntent);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please select a travel type", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void updateButtonSelection(AppCompatButton button, boolean isSelected) {
        button.setSelected(isSelected);
        if (isSelected) {
            button.setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.light_orange))
            );
        } else {
            button.setBackgroundTintList(null);
            button.setBackgroundResource(R.drawable.button_background_selected);
        }
    }



    private void setButtonClickListener(AppCompatButton button) {
        button.setOnClickListener(v -> {
            boolean isSelected = button.isSelected();

            updateButtonSelection(travellingSoloButton, false);
            updateButtonSelection(travellingCoupleButton, false);
            updateButtonSelection(travellingFamilyButton, false);
            updateButtonSelection(travellingGroupButton, false);

            updateButtonSelection(button, !isSelected);

            TextInputLayout adultLayout = findViewById(R.id.adultLayout);
            TextInputLayout childLayout = findViewById(R.id.childLayout);
            if (button.getId() == R.id.travellingFamilyId || button.getId() == R.id.travellingGroupId) {
                adultLayout.setVisibility(View.VISIBLE);
                childLayout.setVisibility(View.VISIBLE);

                String interaction = button.getText().toString();
                int adultCount = getAdultCount();
                int childCount = getChildCount();

                storeUserInteraction(interaction, adultCount, childCount);


            } else {
                adultLayout.setVisibility(View.GONE);
                childLayout.setVisibility(View.GONE);
            }
        });
    }
    private void storeUserInteraction(String interaction, int adultCount, int childCount) {
        try (DbHelper dbHelper = new DbHelper(this)) {
            dbHelper.insertInteraction(interaction, adultCount, childCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private int getAdultCount() {
        TextInputEditText adultEditText = findViewById(R.id.adultEditText);

        String adultCountString = Objects.requireNonNull(adultEditText.getText()).toString();
        if (adultCountString.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(adultCountString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    private int getChildCount() {
        TextInputEditText childEditText = findViewById(R.id.childEditText);

        String childCountString = Objects.requireNonNull(childEditText.getText()).toString();
        if (childCountString.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(childCountString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    private String getSelectedTravelType() {
        return "Solo Travel";
    }


}
