package com.example.tourist_activities.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "city",fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Location> locationSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @JsonManagedReference
    private Region region;

    public City(String name) {
        this.name = name;
    }

    public City() {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
