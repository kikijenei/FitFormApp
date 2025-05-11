package com.example.fitform.repository;
import android.content.Context;

import com.example.fitform.data.Review;
import com.example.fitform.network.ApiService;
import com.example.fitform.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ReviewRepository {
    private final ApiService apiService;

    public ReviewRepository(Context context) {
        apiService = new RetrofitInstance().createService(ApiService.class, context);
    }

    public void getReviewsForWorkout(String token, int workoutId, ReviewCallback<List<Review>> callback) {
        apiService.getReviewsForWorkout(token, workoutId).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch reviews.");
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void createReview(String token, Review review, ReviewCallback<Review> callback) {
        apiService.createReview(token, review).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create review.");
                }
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public void deleteReview(String token, int id, ReviewCallback<Void> callback) {
        apiService.deleteReview(token, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Failed to delete review.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

    public interface ReviewCallback<T> {
        void onSuccess(T result);
        void onFailure(String errorMessage);
    }

}
