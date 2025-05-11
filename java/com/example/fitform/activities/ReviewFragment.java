package com.example.fitform.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitform.R;
import com.example.fitform.data.Review;
import com.example.fitform.repository.ReviewRepository;

import java.util.List;
public class ReviewFragment extends Fragment {

    private ListView reviewListView;
    private EditText commentInput;
    private RatingBar ratingBar;
    private Button submitButton;
    private ReviewRepository reviewRepository;
    private String token;
    private int userId;
    private int workoutId = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        //reviewListView = view.findViewById(R.id.reviewListView);
        commentInput = view.findViewById(R.id.commentInput);
        ratingBar = view.findViewById(R.id.ratingBar);
        submitButton = view.findViewById(R.id.submitButton);

        reviewRepository = new ReviewRepository(requireContext());

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("FitFormPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("auth_token", "");
        userId = sharedPreferences.getInt("user_id", -1);

        if (!token.isEmpty() && userId != -1) {
            loadReviews();
        } else {
            Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
        }

        submitButton.setOnClickListener(v -> submitReview());

        return view;
    }

    private void loadReviews() {
//        reviewRepository.getReviewsForWorkout(token, workoutId, new ReviewRepository.ReviewCallback<List<Review>>() {
//            @Override
//            public void onSuccess(List<Review> reviews) {
//                ReviewListAdapter adapter = new ReviewListAdapter(requireContext(), reviews);
//                reviewListView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void submitReview() {
        String comment = commentInput.getText().toString().trim();
        int rating = (int) ratingBar.getRating();

        if (comment.isEmpty() || rating == 0) {
            Toast.makeText(requireContext(), "Complete review fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Review newReview = new Review();
        newReview.setUserId(userId);
        newReview.setWorkoutId(workoutId);
        newReview.setComment(comment);
        newReview.setRating(rating);

        reviewRepository.createReview(token, newReview, new ReviewRepository.ReviewCallback<Review>() {
            @Override
            public void onSuccess(Review review) {
                Toast.makeText(requireContext(), "Review Added!", Toast.LENGTH_SHORT).show();
                commentInput.setText("");
                ratingBar.setRating(0);
                loadReviews();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}