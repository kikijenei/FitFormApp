package com.example.fitform.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.fitform.R;
import com.example.fitform.data.User;
import com.example.fitform.dialog.EditProfileDialog;
import com.example.fitform.repository.UserRepository;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    TextView tvFullName, tvEmail, tvHeight, tvWeight, tvBirthDate;
    Button btnEditProfile;
    User currentUser;
    UserRepository userRepository;
    String token;
    int userId;

    Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvFullName = view.findViewById(R.id.tvFullName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvHeight = view.findViewById(R.id.tvHeight);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvBirthDate = view.findViewById(R.id.tvBirthDate);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);

        btnLogout = view.findViewById(R.id.btnLogOut);

        btnEditProfile.setOnClickListener(v->{
            new EditProfileDialog().show(getParentFragmentManager(), "EditProfileDialog");
        });

        btnLogout.setOnClickListener(v->{
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("FitFormPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();

        });

        userRepository = new UserRepository(requireContext());

        checkAuthentication();

        return view;
    }

    private void checkAuthentication() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("FitFormPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("auth_token", null);
        userId = sharedPreferences.getInt("user_id", -1);
        if (token != null && userId != -1) {
            loadUserData();
        } else {
            Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadUserData() {
        Log.d("ProfileFragment", "User ID from SharedPreferences: " + userId);
        Log.d("ProfileFragment", "Token: " + token);
        userRepository.getUserById(token, userId, new UserRepository.UserCallback<User>() {
            @Override
            public void onSuccess(User user) {
                currentUser = user;
                updateUI(user);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProfileFragment", "Failed to fetch user: " + errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateUI(User user) {
        if (user != null) {
            Log.d("ProfileFragment", "Updating UI with: " + user.getEmail());
            tvFullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            tvEmail.setText(user.getEmail());
            tvHeight.setText(String.format("Height: %s cm", user.getHeight()));
            tvWeight.setText(String.format("Weight: %s kg", user.getWeight()));
            tvBirthDate.setText(String.format("Birth Date: %s", user.getBirthDate()));

            setupEditButton(user);
        }
    }

    private void setupEditButton(User user) {
        btnEditProfile.setOnClickListener(v -> {
            EditProfileDialog dialog = new EditProfileDialog(user, updatedUser -> {
                userRepository.updateUser(token, userId, updatedUser, new UserRepository.UserCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        Toast.makeText(requireContext(), "Profile updated!", Toast.LENGTH_SHORT).show();
                        currentUser = updatedUser;
                        updateUI(updatedUser);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(requireContext(), "Update failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            });
            dialog.show(getParentFragmentManager(), "EditProfileDialog");
        });
    }
}