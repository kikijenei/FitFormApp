package com.example.fitform.repository;
import android.content.Context;
import com.example.fitform.data.Exercise;
import com.example.fitform.network.ApiService;
import com.example.fitform.network.RetrofitInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ExerciseRepository {

    private final ApiService apiService;

    public ExerciseRepository(Context context) {
        apiService = new RetrofitInstance().createService(ApiService.class, context);
    }
    public void getExercises(String token, ExerciseCallback<List<Exercise>> callback) {
        apiService.getExercises(token).enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch exercises.");
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void createExercise(String token, Exercise exercise, ExerciseCallback<Exercise> callback) {
        apiService.createExercise(token, exercise).enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create exercise.");
                }
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }
    public void getExercisesForWorkout(String token, int workoutId, ExerciseCallback<List<Exercise>> callback) {
        apiService.getExercisesForWorkout(token, workoutId).enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch exercises for workout.");
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void updateExercise(String token, int id, Exercise exercise, ExerciseCallback<Void> callback) {
        apiService.updateExercise(token, id, exercise).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to update exercise.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void deleteExercise(String token, int id, ExerciseCallback<Void> callback) {
        apiService.deleteExercise(token, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to delete exercise.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }
    public interface ExerciseCallback<T> {
        void onSuccess(T result);
        void onFailure(String errorMessage);
    }

}
