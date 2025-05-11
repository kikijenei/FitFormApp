package com.example.fitform.data;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private float height;
    private float weight;
    private String birthDate;
    private String role;
    private Progress progressRecord;
    private List<Workout> workouts;
    private List<Review> reviews;
    private Integer trainerId;
    private List<User> clients;
    private String token;

    public User() {}

    //getter setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public float getHeight() { return height; }
    public void setHeight(float height) { this.height = height; }

    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Progress getProgressRecord() { return progressRecord; }
    public void setProgressRecord(Progress progressRecord) { this.progressRecord = progressRecord; }

    public List<Workout> getWorkouts() { return workouts; }
    public void setWorkouts(List<Workout> workouts) { this.workouts = workouts; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public Integer getTrainerId() { return trainerId; }
    public void setTrainerId(Integer trainerId) { this.trainerId = trainerId; }

    public List<User> getClients() { return clients; }
    public void setClients(List<User> clients) { this.clients = clients; }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
