package com.example.tourist_activities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.Month;

@Entity
@Table(name = "LocationActivity")
public class LocationActivity {

    @EmbeddedId
    private LocationActivityId id;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;

    @ManyToOne
    @MapsId("activityId")
    @JoinColumn(name = "activity_id")
    @JsonManagedReference
    private Activity activity;

    @Column(name = "start", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month startMonth;


    @Column(name = "end", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month endMonth;

    @Column(name = "price", nullable = false)
    private double price;

    public LocationActivity() {
    }

    public LocationActivity(LocationActivityId id, Location location, Activity activity, Month startMonth, Month endMonth, double price) {
        this.id = id;
        this.location = location;
        this.activity = activity;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.price = price;
    }

    public LocationActivity(Location location, Activity activity, Month startMonth, Month endMonth, double price) {
        this.location = location;
        this.activity = activity;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.price = price;
    }

    public LocationActivityId getId() {
        return id;
    }

    public void setId(LocationActivityId id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Month getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Month startMonth) {
        this.startMonth = startMonth;
    }

    public Month getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Month endMonth) {
        this.endMonth = endMonth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
