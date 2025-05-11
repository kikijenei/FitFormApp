package com.example.fitform.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fitform.R;
import com.example.fitform.data.User;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProfileDialog extends DialogFragment {
    EditText etFirstName, etLastName, etEmail, etHeight, etWeight, etBirthDate;
    User user;
    ProfileUpdateListener listener;

    public EditProfileDialog() {

    }

    public interface ProfileUpdateListener {
        void onProfileUpdated(User updatedUser);
    }
    public EditProfileDialog(User user, ProfileUpdateListener listener) {
        this.user = user;
        this.listener = listener;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_edit_profile, null);

//        initViews(view);
//        populateFields();
//        setupDatePicker();

        return new android.app.AlertDialog.Builder(requireContext())
                .setView(R.layout.dialog_edit_profile)
                .create();
//        builder.setView(view)
//                .setTitle("Edit Profile")
//                .setNegativeButton("Cancel", (dialog, which) -> dismiss())
//                .setPositiveButton("Save", (dialog, which) -> saveProfile());
//
//        return builder.create();
    }

    private void initViews(View view) {
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etEmail = view.findViewById(R.id.etEmail);
        etHeight = view.findViewById(R.id.etHeight);
        etWeight = view.findViewById(R.id.etWeight);
        etBirthDate = view.findViewById(R.id.etBirthDate);
    }

    private void populateFields() {
        etFirstName.setText(user.getFirstName());
        etLastName.setText(user.getLastName());
        etEmail.setText(user.getEmail());
        etHeight.setText(String.valueOf(user.getHeight()));
        etWeight.setText(String.valueOf(user.getWeight()));
        etBirthDate.setText(user.getBirthDate());
    }

    private void setupDatePicker() {
        etBirthDate.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Birth Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .format(new Date(selection));
            etBirthDate.setText(formattedDate);
        });

        datePicker.show(getParentFragmentManager(), "DATE_PICKER");
    }

    private void saveProfile() {
        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setFirstName(etFirstName.getText().toString());
        updatedUser.setLastName(etLastName.getText().toString());
        updatedUser.setEmail(etEmail.getText().toString());
        updatedUser.setHeight(Float.parseFloat(etHeight.getText().toString()));
        updatedUser.setWeight(Float.parseFloat(etWeight.getText().toString()));
        updatedUser.setBirthDate(etBirthDate.getText().toString());

        if (listener != null) {
            listener.onProfileUpdated(updatedUser);
        }
    }
}
