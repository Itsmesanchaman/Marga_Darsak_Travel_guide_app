package com.example.autoimageslider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

public class StepTwo extends AppCompatActivity {

    private AppCompatButton planningId;
    private AppCompatButton ownDate;
    private AppCompatButton approxDate;

    private String visitLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);

        AppCompatButton BackId = findViewById(R.id.BackId);
        BackId.setOnClickListener(v -> onBackPressed());

        AppCompatButton continueButton = findViewById(R.id.continueId);
        planningId = findViewById(R.id.planningId);
        ownDate = findViewById(R.id.ownDateId);
        approxDate = findViewById(R.id.approxDateId);


        setButtonClickListener(planningId);
        setButtonClickListener(ownDate);
        setButtonClickListener(approxDate);

        continueButton.setOnClickListener(v -> {

            boolean isAnyButtonSelected = planningId.isSelected() ||
                    ownDate.isSelected() ||
                    approxDate.isSelected();

            if (isAnyButtonSelected) {
                String interaction = "";

                if (planningId.isSelected()) {
                    interaction = planningId.getText().toString();
                } else if (ownDate.isSelected()) {
                    interaction = ownDate.getText().toString();
                } else if (approxDate.isSelected()) {
                    interaction = approxDate.getText().toString();
                }

                String selectedDate = null;
                if (ownDate.isSelected()) {
                    TextInputEditText ownDateEditText = findViewById(R.id.ownDateEditText);
                    selectedDate = Objects.requireNonNull(ownDateEditText.getText()).toString();

                } else if (approxDate.isSelected()) {
                    TextInputEditText approxDateEditText = findViewById(R.id.approxDateEditText);
                    selectedDate = Objects.requireNonNull(approxDateEditText.getText()).toString();
                }

                DataSender.sendDataWithoutTripSelection(this, interaction, selectedDate);

                String selectedData = getSelectedData();

                visitLocation = "Travel data: " + selectedData;
                storeUserInteraction(visitLocation, selectedDate);



                Intent broadcastIntent = new Intent("custom-event-name");
                broadcastIntent.putExtra("visitLocation", visitLocation);
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);


                Intent stepThreeIntent = new Intent(StepTwo.this, StepThree.class);
                startActivity(stepThreeIntent);

            } else {
                Toast.makeText(getApplicationContext(), "Please select a travel date", Toast.LENGTH_SHORT).show();
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

            updateButtonSelection(planningId, false);
            updateButtonSelection(ownDate, false);
            updateButtonSelection(approxDate, false);

            updateButtonSelection(button, !isSelected);

            TextInputLayout owndateLayout = findViewById(R.id.owndateLayout);
            TextInputLayout dateOwnLayout = findViewById(R.id.dateOwnLayout);
            TextInputLayout approxDateLayout = findViewById(R.id.approxDateLayout);
            TextInputLayout dateApproxLayout = findViewById(R.id.dateApproxLayout);

            if (button.getId() == R.id.ownDateId) {
                owndateLayout.setVisibility(View.VISIBLE);
                dateOwnLayout.setVisibility(View.VISIBLE);
                approxDateLayout.setVisibility(View.GONE);
                dateApproxLayout.setVisibility(View.GONE);
            } else if (button.getId() == R.id.approxDateId) {
                owndateLayout.setVisibility(View.GONE);
                dateOwnLayout.setVisibility(View.GONE);
                approxDateLayout.setVisibility(View.VISIBLE);
                dateApproxLayout.setVisibility(View.VISIBLE);
            } else if (button.getId() == R.id.planningId) {
                owndateLayout.setVisibility(View.GONE);
                dateOwnLayout.setVisibility(View.GONE);
                approxDateLayout.setVisibility(View.GONE);
                dateApproxLayout.setVisibility(View.GONE);
            }

            String selectedDate = null;
            if (button.getId() == R.id.ownDateId) {
                TextInputEditText ownDateEditText = findViewById(R.id.ownDateEditText);
                selectedDate = Objects.requireNonNull(ownDateEditText.getText()).toString();
            } else if (button.getId() == R.id.approxDateId) {
                TextInputEditText approxDateEditText = findViewById(R.id.approxDateEditText);
                selectedDate = Objects.requireNonNull(approxDateEditText.getText()).toString();
            }


            storeUserInteraction(button.getText().toString(), selectedDate);

        });


    }


    private void showDatePicker(TextInputLayout layout) {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {


            String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            EditText editText = layout.getEditText();
            if (editText instanceof TextInputEditText) {
                TextInputEditText textInputEditText = (TextInputEditText) editText;
                textInputEditText.setText(selectedDate);
            }
        };


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void setEditTextClickOnListener(TextInputEditText editText, TextInputLayout layout) {
        editText.setFocusable(false);
        editText.setOnClickListener(view -> {
            showDatePicker(layout);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        TextInputLayout owndateLayout = findViewById(R.id.owndateLayout);
        TextInputLayout describeLayout = findViewById(R.id.dateOwnLayout);
        TextInputLayout approxDateLayout = findViewById(R.id.approxDateLayout);
        TextInputLayout dateApproxLayout = findViewById(R.id.dateApproxLayout);

        TextInputEditText ownDateEditText = findViewById(R.id.ownDateEditText);
        TextInputEditText dateOwnEditText = findViewById(R.id.dateOwnEditText);
        TextInputEditText approxDateEditText = findViewById(R.id.approxDateEditText);
        TextInputEditText dateApproxEditText = findViewById(R.id.dateApproxEditText);

        setEditTextClickOnListener(ownDateEditText, owndateLayout);
        setEditTextClickOnListener(dateOwnEditText, describeLayout);
        setEditTextClickOnListener(approxDateEditText, approxDateLayout);
        setEditTextClickOnListener(dateApproxEditText, dateApproxLayout);
    }

    private void storeUserInteraction(String interaction, String selectedDate) {

        try (DbHelper dbHelper = new DbHelper(this)) {
            dbHelper.insertInteraction(interaction, selectedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSelectedData() {
        return "No still planning";
    }


}