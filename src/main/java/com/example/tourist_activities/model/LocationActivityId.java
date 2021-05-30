package com.example.tourist_activities.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocationActivityId implements Serializable {

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "activity_id")
    private int activityId;

    public LocationActivityId() {
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationActivityId that = (LocationActivityId) o;
        return locationId == that.locationId && activityId == that.activityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, activityId);
    }
}
