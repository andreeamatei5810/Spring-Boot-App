package com.example.tourist_activities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "region",fetch = FetchType.EAGER)
    @JsonBackReference
    Set<City> citySet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "countryId", nullable = false)
    @JsonManagedReference
    private Country country;

    public Region(String name) {
        this.name = name;
    }

    public Region() {
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
