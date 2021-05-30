package com.example.tourist_activities.controller;

import com.example.tourist_activities.model.Location;
import com.example.tourist_activities.model.LocationDTO;
import com.example.tourist_activities.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getLocations() {
        return new ResponseEntity<>(locationService.getLocations(), HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocation(@PathVariable("id") int id) {

        return new ResponseEntity<>(locationService.getLocation(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        locationService.addLocation(location);
        return new ResponseEntity<>(location,HttpStatus.CREATED);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public HttpEntity deleteLocation(@PathVariable("id") int id) {
        locationService.deleteLocation(id);
        return new HttpEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public HttpEntity updateLocation(@PathVariable("id") int id, @RequestParam String name) {
        locationService.updateLocation(id, name);
        return new HttpEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public List<LocationDTO> searchLocation(@RequestParam(required = false) List<String> name, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<LocationDTO> locations;
        if (name != null) {
            locations = locationService.searchLocationsWithActivities(name, startDate.getMonth().getValue(), endDate.getMonth().getValue(), startDate.until(endDate, ChronoUnit.DAYS));
        } else {
            locations = locationService.searchLocations(startDate.getMonth().getValue(), endDate.getMonth().getValue(), startDate.until(endDate, ChronoUnit.DAYS));
        }
        return locations;
    }
}
