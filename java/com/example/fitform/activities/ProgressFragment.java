package com.example.fitform.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitform.R;
import com.example.fitform.adapter.ProgressAdapter;

import java.util.ArrayList;
import java.util.List;


public class ProgressFragment extends Fragment {

    private RecyclerView recyclerProgress;
    private ProgressAdapter progressAdapter;
    private List<String> progressList;

    public ProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressFragment newInstance(String param1, String param2) {
        ProgressFragment fragment = new ProgressFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerProgress = view.findViewById(R.id.recyclerProgress);
        recyclerProgress.setLayoutManager(new LinearLayoutManager(getContext()));

        progressList = new ArrayList<>();
        progressList.add("Workout 1: 30 min cardio");
        progressList.add("Workout 2: 45 min strength training");
        progressList.add("Workout 3: 20 min HIIT");

        progressAdapter = new ProgressAdapter(progressList);
        recyclerProgress.setAdapter(progressAdapter);
    }
}