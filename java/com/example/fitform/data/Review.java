package com.example.fitform.data;


public class Review {
    private int id;
    private int workoutId;
    private int userId;
    private String comment;
    private int rating;
    private String createdAt;

    //getter + setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getWorkoutId() { return workoutId; }
    public void setWorkoutId(int workoutId) { this.workoutId = workoutId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}