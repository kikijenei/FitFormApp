package com.example.fitform.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fitform.data.User;
import com.example.fitform.network.ApiService;
import com.example.fitform.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private final ApiService apiService;

    public UserRepository(Context context) {
        apiService = new RetrofitInstance().createService(ApiService.class, context);
    }

    public void getUsers(String token, UserCallback<List<User>> callback) {
        apiService.getUsers(token).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch users.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void getUserById(String token, int id, UserCallback<User> callback) {
        Log.d("API_REQUEST", "Token: " + token);
        Log.d("API_REQUEST", "User ID: " + id);
        apiService.getUserById("Bearer "+ token, id).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API_RESPONSE", "User primit: " + response.body().getEmail());
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch user.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void updateUser(String token, int id, User user, UserCallback<Void> callback) {
        apiService.updateUser(token, id, user).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to update user.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void deleteUser(String token, int id, UserCallback<Void> callback) {
        apiService.deleteUser(token, id).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to delete user.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public interface UserCallback<T> {
        void onSuccess(T result);
        void onFailure(String errorMessage);
    }

}
