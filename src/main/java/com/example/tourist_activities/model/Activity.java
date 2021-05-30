package com.example.tourist_activities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, name = "name")
    private String activityName;

    @OneToMany(mappedBy = "activity",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<LocationActivity> locationActivities = new ArrayList<>();

    public Activity() {
    }

    public Activity(String name) {
        this.activityName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

}
