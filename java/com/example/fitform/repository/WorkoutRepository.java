package com.example.fitform.repository;

import android.content.Context;
import com.example.fitform.data.Workout;
import com.example.fitform.network.ApiService;
import com.example.fitform.network.RetrofitInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class WorkoutRepository {

    private final ApiService apiService;

    public WorkoutRepository(Context context) {
        apiService = new RetrofitInstance().createService(ApiService.class, context);
    }

    public void getWorkouts(String token, WorkoutCallback<List<Workout>> callback) {
        apiService.getWorkouts(token).enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch workouts.");
                }
            }

            @Override
            public void onFailure(Call<List<Workout>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void getWorkoutById(String token, int id, WorkoutCallback<Workout> callback) {
        apiService.getWorkout(token, id).enqueue(new Callback<Workout>() {
            @Override
            public void onResponse(Call<Workout> call, Response<Workout> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch workout details.");
                }
            }

            @Override
            public void onFailure(Call<Workout> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void createWorkout(String token, Workout workout, WorkoutCallback<Workout> callback) {
        apiService.createWorkout(token, workout).enqueue(new Callback<Workout>() {
            @Override
            public void onResponse(Call<Workout> call, Response<Workout> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create workout.");
                }
            }

            @Override
            public void onFailure(Call<Workout> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void updateWorkout(String token, int id, Workout workout, WorkoutCallback<Void> callback) {
        apiService.updateWorkout(token, id, workout).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to update workout.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void deleteWorkout(String token, int id, WorkoutCallback<Void> callback) {
        apiService.deleteWorkout(token, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to delete workout.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public interface WorkoutCallback<T> {
        void onSuccess(T result);
        void onFailure(String errorMessage);
    }
}
