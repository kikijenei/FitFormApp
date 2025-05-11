package com.example.fitform.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitform.R;
import com.example.fitform.data.LoginRequest;
import com.example.fitform.data.User;
import com.example.fitform.repository.AuthRepository;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;
    TextView registerTextView;
    AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        authRepository = new AuthRepository(this);


        loginButton.setOnClickListener(v -> loginUser());
        registerTextView.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

    }
    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(email, password);
        authRepository.login(loginRequest, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                getSharedPreferences("FitFormPrefs", MODE_PRIVATE)
                        .edit()
                        .putString("auth_token", user.getToken())
                        .putInt("user_id", user.getId())
                        .apply();

                Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}