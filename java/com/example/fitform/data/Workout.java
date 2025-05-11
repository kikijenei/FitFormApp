package com.example.fitform.data;


import java.util.List;

public class Workout {
    private int id;
    private String type;
    private int userId;
    private String dateAndTime;
    private int duration;
    private String description;
    private List<Exercise> exercises;
    private List<Review> reviews;

    //getter + setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getDateAndTime() { return dateAndTime; }
    public void setDateAndTime(String dateAndTime) { this.dateAndTime = dateAndTime; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Exercise> getExercises() { return exercises; }
    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
