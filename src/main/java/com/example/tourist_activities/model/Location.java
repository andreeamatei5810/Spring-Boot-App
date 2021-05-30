package com.example.tourist_activities.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonManagedReference
    private City city;

    @OneToMany(mappedBy = "location",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<LocationActivity> locationActivities = new ArrayList<>();

    public Location(String name) {
        this.name = name;
    }

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int placeId) {
        this.id = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocationActivity> getLocationActivities() {
        return locationActivities;
    }

    public void setLocationActivities(List<LocationActivity> locationActivities) {
        this.locationActivities = locationActivities;
    }
}
