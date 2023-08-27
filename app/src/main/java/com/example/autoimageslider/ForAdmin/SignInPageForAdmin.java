package com.example.autoimageslider.ForAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autoimageslider.R;

public class SignInPageForAdmin extends AppCompatActivity {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "Admin@2000";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page_for_admin);

        EditText usernameForAdmin = findViewById(R.id.usernameForAdmin);
        EditText passwordForAdmin = findViewById(R.id.passwordForAdmin);
        Button loginBtnForAdmin = findViewById(R.id.loginBtnForAdmin);

        loginBtnForAdmin.setOnClickListener(view -> {
            String enteredUsername = usernameForAdmin.getText().toString();
            String enteredPassword = passwordForAdmin.getText().toString();

            if (isValidAdminCredentials(enteredUsername, enteredPassword)) {
                startActivity(new Intent(SignInPageForAdmin.this, Dashboard.class));
            } else {
                Toast.makeText(SignInPageForAdmin.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidAdminCredentials(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}