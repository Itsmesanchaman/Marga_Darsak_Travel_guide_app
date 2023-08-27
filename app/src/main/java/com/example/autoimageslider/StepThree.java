package com.example.autoimageslider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class StepThree extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three);

        TextView wherevisitId = findViewById(R.id.wherevisitId);
        TextView SelectTripId = findViewById(R.id.SelectTripId);
        TextInputEditText visitWhereEditText = findViewById(R.id.visitWhereEditText);
        TextInputLayout whereVisitLayout = findViewById(R.id.whereVisitLayout);
        AppCompatButton NextToId = findViewById(R.id.NextToId);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton rdButtonOneId = findViewById(R.id.rdButtonOneId);
        RadioButton rdButtonTwoId = findViewById(R.id.rdButtonTwoId);
        Spinner tripSelectId = findViewById(R.id.tripSelectId);


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rdButtonOneId) {

                wherevisitId.setVisibility(View.VISIBLE);
                whereVisitLayout.setVisibility(View.VISIBLE);
                SelectTripId.setVisibility(View.GONE);
                tripSelectId.setVisibility(View.GONE);
            } else if (checkedId == R.id.rdButtonTwoId) {

                wherevisitId.setVisibility(View.GONE);
                whereVisitLayout.setVisibility(View.GONE);
                SelectTripId.setVisibility(View.VISIBLE);
                tripSelectId.setVisibility(View.VISIBLE);
            }



            NextToId.setOnClickListener(v -> {
                String interaction;
                String visitLocation;
                String selectedTripType;
                String tripSelection;

                if (rdButtonOneId.isChecked()) {
                    interaction = rdButtonOneId.getText().toString();
                    visitLocation = Objects.requireNonNull(visitWhereEditText.getText()).toString();
                    selectedTripType = "";
                } else if (rdButtonTwoId.isChecked()) {
                    interaction = rdButtonTwoId.getText().toString();
                    visitLocation = "";
                    selectedTripType = getSelectedTripType();

                } else {

                    return;
                }

                String selectedLocation = getSelectedLocation();

                tripSelection = "Trip: " + selectedTripType + ": " + selectedLocation;


                Intent broadcastIntent = new Intent("custom-event-name");
                broadcastIntent.putExtra("tripSelection", tripSelection);
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);


                Log.d("StepThree", "Broadcast sent.");

                storeUserInteraction(interaction, visitLocation, tripSelection);



                Intent stepFourIntent  = new Intent(StepThree.this, StepFour.class);
                startActivity(stepFourIntent);
            });


        });



        String [] places = {"Mardi Himal Trek", "Annapurna Circuit Trek","Champadevi Hiking Trail","Pokhara", "Rara Lake", "Muktinath Temple"
        , "Kalinchowk", "Chitwan National Park", "Nagarkot","Everest Base Camp", "Kanchenjunga Base Camp"
        };
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, places);
        tripSelectId.setAdapter(adapter);

        tripSelectId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select a trip", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void storeUserInteraction(String interaction, String visitLocation, String tripSelection) {
        try (DbHelper dbHelper = new DbHelper(this)) {
            dbHelper.insertInteraction(interaction, visitLocation, tripSelection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSelectedTripType() {
        return "Expert";
    }

    private String getSelectedLocation() {
        return "Everest Base Camp";
    }
}