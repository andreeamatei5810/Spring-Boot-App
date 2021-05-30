package com.example.tourist_activities.model;

public class LocationDTO {
    private String locationName;
    private String[] activities;
    private double cost;

    public String[] getActivities() {
        return activities;
    }

    public void setActivities(String[] activities) {
        this.activities = activities;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
