package com.example.tourist_activities.repository;

import com.example.tourist_activities.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT DISTINCT u FROM Location u JOIN LocationActivity t ON t.location.id = u.id JOIN Activity e ON e.id = t.activity.id WHERE e.activityName IN ?1 AND t.startMonthId<= ?2 AND t.endMonthId>= ?3")
    List<Location> userSearchWithActivities(List<String> activities, int startMonth, int endMonth);

    @Query("SELECT DISTINCT u FROM Location u JOIN LocationActivity t ON t.location.id = u.id WHERE t.startMonthId<= ?1 AND t.endMonthId>= ?2")
    List<Location> userSearch(int startMonth, int endMonth);

}