package com.example.fitform.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitform.R;
import com.example.fitform.data.Exercise;
import com.example.fitform.repository.ExerciseRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class ExerciseFragment extends Fragment {
    private RecyclerView recyclerView;
    //private ExerciseAdapter exerciseAdapter;
    //private FloatingActionButton fabAddExercise;
    private ExerciseRepository exerciseRepository;
    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        //recyclerView = view.findViewById(R.id.recyclerViewExercises);
        //FloatingActionButton fabAddExercise = view.findViewById(R.id.fabAddExercise);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //exerciseRepository = new ExerciseRepository(getContext());
        //token = SessionManager.getInstance(getContext()).getToken();

        //loadExercises();

        //fabAddExercise.setOnClickListener(v -> addExercise());

        return view;
    }

//    private void loadExercises() {
//        exerciseRepository.getExercises(token, new ExerciseRepository.ExerciseCallback<List<Exercise>>() {
//            @Override
//            public void onSuccess(List<Exercise> exercises) {
//                exerciseAdapter = new ExerciseAdapter(exercises);
//                recyclerView.setAdapter(exerciseAdapter);
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void addExercise() {
        Toast.makeText(getContext(), "Add Exercise Clicked", Toast.LENGTH_SHORT).show();
    }
}