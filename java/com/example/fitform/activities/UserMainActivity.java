package com.example.fitform.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.fitform.R;

public class UserMainActivity extends AppCompatActivity {
    private static final int MENU_PROFILE = 1;
    private static final int MENU_REVIEW = 2;
    private static final int MENU_PROGRESS = 3;
    private static final int MENU_WORKOUT = 4;
    private static final int MENU_EXERCISE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String fragmentName = "";
            int menuNumber = getMenuNumber(item.getItemId());
            switch (menuNumber) {
                case MENU_PROFILE:
                    selectedFragment = new ProfileFragment();
                    fragmentName = "ProfileFragment";
                    break;
                case MENU_REVIEW:
                    selectedFragment = new ReviewFragment();
                    fragmentName = "ReviewFragment";
                    break;
                case MENU_PROGRESS:
                    selectedFragment = new ProgressFragment();
                    fragmentName = "ProgressFragment";
                    break;
                case MENU_WORKOUT:
                    selectedFragment = new WorkoutFragment();
                    fragmentName = "WorkoutFragment";
                    break;
                case MENU_EXERCISE:
                    selectedFragment = new ExerciseFragment();
                    fragmentName = "ExerciseFragment";
                    break;
            }

            if (selectedFragment != null) {
                Log.d("UserMainActivity", "Fragment switched to: " + fragmentName);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
    private int getMenuNumber(int itemId) {
        if (itemId == R.id.nav_profile) return MENU_PROFILE;
        if (itemId == R.id.nav_review) return MENU_REVIEW;
        if (itemId == R.id.nav_progress) return MENU_PROGRESS;
        if (itemId == R.id.nav_workout) return MENU_WORKOUT;
        if (itemId == R.id.nav_exercise) return MENU_EXERCISE;
        return -1;
    }
}