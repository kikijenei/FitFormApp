package com.example.fitform.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitform.R;
import com.example.fitform.adapter.WorkoutAdapter;
import com.example.fitform.data.Exercise;
import com.example.fitform.data.Workout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WorkoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkoutAdapter adapter;
    private FloatingActionButton fabAddWorkout;
    private List<Workout> workoutList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        initViews(view);
        setupRecyclerView();
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewWorkouts);
        fabAddWorkout = view.findViewById(R.id.fabAddWorkout);

        fabAddWorkout.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Add new workout clicked", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupRecyclerView() {
        adapter = new WorkoutAdapter(workoutList, workout -> {
            Toast.makeText(requireContext(), "Selected: " + workout.getType(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

}