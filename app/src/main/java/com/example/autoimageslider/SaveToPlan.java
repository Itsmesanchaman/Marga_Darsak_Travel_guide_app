package com.example.autoimageslider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

public class SaveToPlan extends AppCompatActivity {

    private Button saveButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savetoplan);

        ImageView backToH = findViewById(R.id.backtoh);
        backToH.setOnClickListener(v -> onBackPressed());


        TextInputEditText textInputEditText = findViewById(R.id.textInputEditText);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_white));
        saveButton.setTextColor(ContextCompat.getColor(this, R.color.light_white));

        textInputEditText.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                saveButton.setVisibility(View.VISIBLE); // Show the save button when text is entered
            } else {
                saveButton.setVisibility(View.INVISIBLE); // Hide the save button when no text is entered
            }
        }
    };



}
