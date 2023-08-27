package com.example.autoimageslider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class WithEmail extends AppCompatActivity {

    private DbHelper sqliteHelper;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText userEmailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withemail);

        sqliteHelper = new DbHelper(this);

        usernameEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.CpasswordEditText);
        Button signUpButton = findViewById(R.id.buttonsignup);
        userEmailEditText = findViewById(R.id.userEmailEditText);
        signUpButton.setOnClickListener(v -> registerUser());

        ImageView backToSigninPage = findViewById(R.id.backtosigninpage);
        backToSigninPage.setOnClickListener(back -> onBackPressed());




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


    }



    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String userEmail = userEmailEditText.getText().toString().trim();


        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6 || password.length() > 12) {
            Toast.makeText(this, "Password must be between 6 and 12 characters long", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = sqliteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("password", password);
            values.put("email", userEmail);



            long result = db.insert("users", null, values);
            db.close();

            if (result != -1) {
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void navigateToLogin() {
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(WithEmail.this, SignInPage.class);
        startActivity(intent);
        finish();
    }

}
