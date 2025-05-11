package com.example.fitform.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitform.R;
import com.example.fitform.data.Workout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private List<Workout> workouts;
    private OnWorkoutClickListener listener;

    public interface OnWorkoutClickListener {
        void onWorkoutClick(Workout workout);
    }

    public WorkoutAdapter(List<Workout> workouts, OnWorkoutClickListener listener) {
        this.workouts = workouts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.bind(workout);
        holder.itemView.setOnClickListener(v -> listener.onWorkoutClick(workout));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void updateWorkouts(List<Workout> newWorkouts) {
        workouts = newWorkouts;
        notifyDataSetChanged();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvWorkoutType;
        private final TextView tvWorkoutDuration;
        private final TextView tvWorkoutDate;
        private final TextView tvWorkoutDescription;
        private final TextView tvExerciseCount;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWorkoutType = itemView.findViewById(R.id.tvWorkoutType);
            tvWorkoutDuration = itemView.findViewById(R.id.tvWorkoutDuration);
            tvWorkoutDate = itemView.findViewById(R.id.tvWorkoutDate);
            tvWorkoutDescription = itemView.findViewById(R.id.tvWorkoutDescription);
            tvExerciseCount = itemView.findViewById(R.id.tvExerciseCount);
        }

        public void bind(Workout workout) {
            tvWorkoutType.setText(workout.getType());
            tvWorkoutDuration.setText(formatDuration(workout.getDuration()));
            tvWorkoutDate.setText(formatDate(workout.getDateAndTime()));
            tvWorkoutDescription.setText(workout.getDescription());

            int exerciseCount = workout.getExercises() != null ? workout.getExercises().size() : 0;
            tvExerciseCount.setText(itemView.getContext().getString(exerciseCount));
        }

        private String formatDuration(int minutes) {
            return minutes > 60 ?
                    String.format("%dh %02dm", minutes / 60, minutes % 60) :
                    String.format("%d min", minutes);
        }

        private String formatDate(String dateString) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, MMM d • h:mm a", Locale.getDefault());
                Date date = inputFormat.parse(dateString);
                return outputFormat.format(date);
            } catch (Exception e) {
                return dateString;
            }
        }
    }
}