package com.example.tourist_activities.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;

    @Column(unique = true,nullable = false)
    private String name;
    private String abbreviation;

    @OneToMany(mappedBy="country",fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Region> regionSet = new HashSet<>();

    public Country(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
