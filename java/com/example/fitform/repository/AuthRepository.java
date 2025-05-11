package com.example.fitform.repository;

import android.content.Context;
import com.example.fitform.data.LoginRequest;
import com.example.fitform.data.RegisterRequest;
import com.example.fitform.data.User;
import com.example.fitform.network.ApiService;
import com.example.fitform.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AuthRepository {

    private final ApiService apiService;

    public AuthRepository(Context context) {
        apiService = new RetrofitInstance().createService(ApiService.class, context);
    }

    public void login(LoginRequest loginRequest, AuthCallback callback) {
        apiService.login(loginRequest).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Login failed. Please check your credentials.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void register(RegisterRequest registerRequest, AuthCallback callback) {
        apiService.register(registerRequest).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Registration failed.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public interface AuthCallback {
        void onSuccess(User user);
        void onFailure(String errorMessage);
    }
}
