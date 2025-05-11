package com.example.fitform.network;

import com.example.fitform.data.LoginRequest;
import com.example.fitform.data.RegisterRequest;
import com.example.fitform.data.User;
import com.example.fitform.data.Exercise;
import com.example.fitform.data.Progress;
import com.example.fitform.data.Review;
import com.example.fitform.data.Workout;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Header;
import java.util.List;
public interface ApiService {

    // LOGIN
    @POST("api/auth/login")
    Call<User> login(@Body LoginRequest loginRequest);

    // REGISTER
    @POST("api/auth/register")
    Call<User> register(@Body RegisterRequest registerRequest);


    //ALL EXERCISE
    @GET("api/exercises")
    Call<List<Exercise>> getExercises(@Header("Authorization") String token);

    //CREATE EXERCISE
    @POST("api/exercises")
    Call<Exercise> createExercise(@Header("Authorization") String token, @Body Exercise exercise);

    //GET EXERCISE-WORKOUT
    @GET("api/exercises/workout/{workoutId}")
    Call<List<Exercise>> getExercisesForWorkout(@Header("Authorization") String token, @Path("workoutId") int workoutId);

    //PUT EXERCISE
    @PUT("api/exercises/{id}")
    Call<Void> updateExercise(@Header("Authorization") String token, @Path("id") int id, @Body Exercise exercise);

    //DELETE EXERCISE
    @DELETE("api/exercises/{id}")
    Call<Void> deleteExercise(@Header("Authorization") String token, @Path("id") int id);

    //GET PROGRESS USER
    @GET("api/progress/user/{userId}")
    Call<List<Progress>> getProgressForUser(@Header("Authorization") String token, @Path("userId") int userId);

    //POST NEW PROGRESS
    @POST("api/progress")
    Call<Progress> createProgress(@Header("Authorization") String token, @Body Progress progress);

    // PUT PROGRESS
    @PUT("api/progress/{id}")
    Call<Void> updateProgress(@Header("Authorization") String token, @Path("id") int id, @Body Progress progress);

    //DELETE PROGRESS
    @DELETE("api/progress/{id}")
    Call<Void> deleteProgress(@Header("Authorization") String token, @Path("id") int id);

    //GET REVIEWS WORKOUT
    @GET("api/reviews/workout/{workoutId}")
    Call<List<Review>> getReviewsForWorkout(@Header("Authorization") String token, @Path("workoutId") int workoutId);

    //POST NEW REVIEW
    @POST("api/reviews")
    Call<Review> createReview(@Header("Authorization") String token, @Body Review review);

    //DELETE REVIEW
    @DELETE("api/reviews/{id}")
    Call<Void> deleteReview(@Header("Authorization") String token, @Path("id") int id);


    //GET USERS
    @GET("api/users")
    Call<List<User>> getUsers(@Header("Authorization") String token);

    //GET USERS BY ID
    @GET("api/users/{id}")
    Call<User> getUserById(@Header("Authorization") String token, @Path("id") int userId);

    //PUT USER ID
    @PUT("api/users/{id}")
    Call<Void> updateUser(@Header("Authorization") String token, @Path("id") int id, @Body User user);

    //DELETE USER ID
    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Header("Authorization") String token, @Path("id") int id);


    //GET WORKOUTS
    @GET("api/workouts")
    Call<List<Workout>> getWorkouts(@Header("Authorization") String token);

    // GET WORKOUT BY ID
    @GET("api/workouts/{id}")
    Call<Workout> getWorkout(@Header("Authorization") String token, @Path("id") int id);

    // POST WORKOUT
    @POST("api/workouts")
    Call<Workout> createWorkout(@Header("Authorization") String token, @Body Workout workout);

    //PUT WORKOUT
    @PUT("api/workouts/{id}")
    Call<Void> updateWorkout(@Header("Authorization") String token, @Path("id") int id, @Body Workout workout);

    //DELETE WORKOUT
    @DELETE("api/workouts/{id}")
    Call<Void> deleteWorkout(@Header("Authorization") String token, @Path("id") int id);

}
