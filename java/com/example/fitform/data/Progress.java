package com.example.fitform.data;

public class Progress {
    private int id;
    private int userId;
    private float weight;
    private int workoutsPerWeek;
    private String measurementDate;

    //getter + setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }

    public int getWorkoutsPerWeek() { return workoutsPerWeek; }
    public void setWorkoutsPerWeek(int workoutsPerWeek) { this.workoutsPerWeek = workoutsPerWeek; }

    public String getMeasurementDate() { return measurementDate; }
    public void setMeasurementDate(String measurementDate) { this.measurementDate = measurementDate; }
}