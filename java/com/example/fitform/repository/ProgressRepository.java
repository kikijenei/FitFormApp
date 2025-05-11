package com.example.fitform.repository;
import android.content.Context;

import com.example.fitform.data.Progress;
import com.example.fitform.network.ApiService;
import com.example.fitform.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProgressRepository {
    private final ApiService apiService;

    public ProgressRepository(Context context) {
        apiService = new RetrofitInstance().createService(ApiService.class, context);
    }

    public void getProgressForUser(String token, int userId, ProgressCallback<List<Progress>> callback) {
        apiService.getProgressForUser(token, userId).enqueue(new Callback<List<Progress>>() {
            @Override
            public void onResponse(Call<List<Progress>> call, Response<List<Progress>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch progress data.");
                }
            }

            @Override
            public void onFailure(Call<List<Progress>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void createProgress(String token, Progress progress, ProgressCallback<Progress> callback) {
        apiService.createProgress(token, progress).enqueue(new Callback<Progress>() {
            @Override
            public void onResponse(Call<Progress> call, Response<Progress> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create progress entry.");
                }
            }

            @Override
            public void onFailure(Call<Progress> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void updateProgress(String token, int id, Progress progress, ProgressCallback<Void> callback) {
        apiService.updateProgress(token, id, progress).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to update progress.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void deleteProgress(String token, int id, ProgressCallback<Void> callback) {
        apiService.deleteProgress(token, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to delete progress entry.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public interface ProgressCallback<T> {
        void onSuccess(T result);
        void onFailure(String errorMessage);
    }

}
