package com.example.autoimageslider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.maps.errors.ApiException;

public class SignInPage extends AppCompatActivity {


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private SharedPreferences sharedPreferences;
    private EditText usernameEditText, passwordEditText;
    private DbHelper sqliteHelper;
    private TextView usernameErrorTextView;
    private TextView passwordErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinpage);

        AppCompatButton adminLoginId = findViewById(R.id.adminLoginId);
        adminLoginId.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.autoimageslider.ForAdmin.SignInPageForAdmin.class);
            startActivity(intent);
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);


        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);





        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
        if (acc != null) {
            navigateToSecondActivity();
            finish();
        }

        AppCompatButton googleSI = findViewById(R.id.googleSI);
        googleSI.setOnClickListener(v -> signIn());

         sqliteHelper = new DbHelper(this);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.emailEditTextID);
        Button loginButton = findViewById(R.id.loginBtn);
        usernameErrorTextView = findViewById(R.id.usernameErrorTextView);
        passwordErrorTextView = findViewById(R.id.passwordErrorTextView);
        TextView forgetPasswordId = findViewById(R.id.forgetPasswordId);

        forgetPasswordId.setOnClickListener(v ->{
            Intent intent = new Intent(SignInPage.this, ResetPassword.class);
            startActivity(intent);
        });



        TextView registerId = findViewById(R.id.registerId);
        registerId.setOnClickListener(v -> {
            Intent intent = new Intent(SignInPage.this, WithEmail.class);
            startActivity(intent);
        });

        TextView skipId = findViewById(R.id.skipId);
        skipId.setOnClickListener(v -> {
            Intent intent = new Intent(SignInPage.this, MainActivity.class);
            startActivity(intent);
        });

        ToggleButton passwordVisibilityToggle = findViewById(R.id.passwordVisibilityToggle);
        passwordVisibilityToggle.setVisibility(View.GONE);

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    passwordVisibilityToggle.setVisibility(View.VISIBLE);
                } else {
                    passwordVisibilityToggle.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        passwordVisibilityToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        usernameEditText.setBackgroundResource(R.drawable.edit_text_border);
        passwordEditText.setBackgroundResource(R.drawable.edit_text_border);
        usernameErrorTextView.setVisibility(View.GONE);
        passwordErrorTextView.setVisibility(View.GONE);

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        boolean hasError = false;

        if (TextUtils.isEmpty(username)) {
            usernameErrorTextView.setText(R.string.please_enter_a_username);
            usernameErrorTextView.setVisibility(View.VISIBLE);
            usernameEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            hasError = true;
        }

        if (TextUtils.isEmpty(password)) {
            passwordErrorTextView.setText(R.string.passthisword);
            passwordErrorTextView.setVisibility(View.VISIBLE);
            passwordEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            hasError = true;
        }

        if (hasError) {
            return;
        }

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            usernameEditText.setBackgroundResource(R.drawable.edit_text_border);
            passwordEditText.setBackgroundResource(R.drawable.edit_text_border);
            usernameErrorTextView.setVisibility(View.GONE);
            passwordErrorTextView.setVisibility(View.GONE);

            saveLoginState();
            navigateToSecondActivity();
            finish();
        } else {
            usernameEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            usernameErrorTextView.setText(R.string.incorrect_username);
            usernameErrorTextView.setVisibility(View.VISIBLE);
            passwordEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            passwordErrorTextView.setText(R.string.incorrect_password);
            passwordErrorTextView.setVisibility(View.VISIBLE);
        }

        cursor.close();
        db.close();
    }

    void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                saveLoginState();
                navigateToSecondActivity();
                finish();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecondActivity() {
        Toast.makeText(getApplicationContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
        Intent mainIntent = new Intent(SignInPage.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void saveLoginState() {
        boolean isLoggedIn = true;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
        finish();
    }
}




