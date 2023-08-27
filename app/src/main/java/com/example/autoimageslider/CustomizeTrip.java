package com.example.autoimageslider;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class CustomizeTrip extends AppCompatActivity {

    private TextInputEditText fullNameEditText;
    private TextInputEditText emailEditTextId;
    private TextInputEditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_trip);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditTextId = findViewById(R.id.emailEditTextId);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

        AppCompatButton cancelGoId = findViewById(R.id.cancelGoId);
        cancelGoId.setOnClickListener(v ->onBackPressed());




        AppCompatButton submitToId = findViewById(R.id.submitToId);
        submitToId.setOnClickListener(v -> {
            String fullName = Objects.requireNonNull(fullNameEditText.getText()).toString().trim();
            String email = Objects.requireNonNull(emailEditTextId.getText()).toString().trim();
            String phoneNumber = Objects.requireNonNull(phoneNumberEditText.getText()).toString().trim();

            if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(CustomizeTrip.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (fullName.isEmpty()) {
                Toast.makeText(CustomizeTrip.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
            } else if (email.isEmpty()) {
                Toast.makeText(CustomizeTrip.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else if (phoneNumber.isEmpty()) {
                Toast.makeText(CustomizeTrip.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            } else {
                submitForm();
            }
        });
    }

    private void submitForm() {

        Toast.makeText(this, "Form submitted successfully", Toast.LENGTH_SHORT).show();
    }
}
