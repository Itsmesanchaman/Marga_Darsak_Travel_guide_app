package com.example.autoimageslider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ResetPassword extends AppCompatActivity {


    private DbHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        ImageView backToSignInPage = findViewById(R.id.backToSignInPage);
        backToSignInPage.setOnClickListener(v ->onBackPressed());

        sqliteHelper = new DbHelper(this);

        AppCompatButton buttonResetId = findViewById(R.id.buttonResetId);
        EditText CNewPasswordEditText = findViewById(R.id.CNewPasswordEditText);
        EditText newPasswordEditText = findViewById(R.id.newPasswordEditText);
        EditText userNameEditTextId = findViewById(R.id.userNameEditTextId);



        ToggleButton passwordVisibleToggleId = findViewById(R.id.passwordVisibleToggleId);
        passwordVisibleToggleId.setVisibility(View.GONE);

        newPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    passwordVisibleToggleId.setVisibility(View.VISIBLE);
                } else {
                    passwordVisibleToggleId.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        passwordVisibleToggleId.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                newPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                newPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            newPasswordEditText.setSelection(newPasswordEditText.getText().length());
        });



        ToggleButton passwordToggleId = findViewById(R.id.passwordToggleId);
        passwordToggleId.setVisibility(View.GONE);

        CNewPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    passwordToggleId.setVisibility(View.VISIBLE);
                } else {
                    passwordToggleId.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        passwordToggleId.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                CNewPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                CNewPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            CNewPasswordEditText.setSelection(CNewPasswordEditText.getText().length());
        });

        buttonResetId.setOnClickListener(v -> resetPassword(
                userNameEditTextId.getText().toString().trim(),
                newPasswordEditText.getText().toString().trim(),
                CNewPasswordEditText.getText().toString().trim()
        ));

    }

    private void resetPassword(String username, String newPassword, String confirmPassword) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (newPassword.length() < 6 || newPassword.length() > 12) {
            Toast.makeText(this, "Password must be between 6 and 12 characters long", Toast.LENGTH_SHORT).show();
        } else if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = sqliteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("password", newPassword);

            int rowsAffected = db.update("users", values, "username=?", new String[]{username});
            db.close();

            if (rowsAffected > 0) {
                Toast.makeText(this, "Successfully reset your password", Toast.LENGTH_SHORT).show();
                navigateToSignInPage();
            } else {
                Toast.makeText(this, "Failed to reset your password. Please check the username.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToSignInPage() {
        Intent intent = new Intent(ResetPassword.this, SignInPage.class);
        startActivity(intent);
        finish();
    }
}
