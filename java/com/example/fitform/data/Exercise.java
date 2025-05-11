package com.example.fitform.data;
public class Exercise {
    private int id;
    private int workoutId;
    private String name;
    private int repetitions;
    private int sets;
    private int duration;
    private String description;

    //getter + setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getWorkoutId() { return workoutId; }
    public void setWorkoutId(int workoutId) { this.workoutId = workoutId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getRepetitions() { return repetitions; }
    public void setRepetitions(int repetitions) { this.repetitions = repetitions; }

    public int getSets() { return sets; }
    public void setSets(int sets) { this.sets = sets; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}