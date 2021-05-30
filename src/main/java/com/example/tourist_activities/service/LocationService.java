package com.example.tourist_activities.service;

import com.example.tourist_activities.exception.ResourceNotFoundException;
import com.example.tourist_activities.model.Location;
import com.example.tourist_activities.model.LocationActivity;
import com.example.tourist_activities.model.LocationDTO;
import com.example.tourist_activities.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public Location getLocation(int id) {
        Optional<Location> location = locationRepository.findById(id);
        if(location.isPresent()){
            return location.get();
        }
        else{
            throw new ResourceNotFoundException("There is no location with this id!");
        }
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(int id) {
        Location location = this.getLocation(id);
        locationRepository.delete(location);
    }

    @Transactional
    public void updateLocation(int id, String name) {
        Location location = this.getLocation(id);
        if (name != null && name.length() > 0) {
            location.setName(name);
        }
    }

    public List<LocationDTO> searchLocationsWithActivities(List<String> activities, int startMonth, int endMonth, long toDays) {
        List<Location> locations;
        locations = locationRepository.userSearchWithActivities(activities, startMonth, endMonth);
        List<LocationDTO> locationDTOS = new ArrayList<>();
        double cost;
        for (Location location : locations) {
            cost = 0;
            for (LocationActivity locationActivity : location.getLocationActivities()) {
                System.out.println();
                if (activities.contains(locationActivity.getActivity().getActivityName())) {
                    cost += locationActivity.getPrice();
                }
            }
            LocationDTO locationDTO = modelMapper.map(location, LocationDTO.class);
            locationDTO.setCost(cost * toDays);
            locationDTOS.add(locationDTO);
        }
        locationDTOS.sort((l1, l2) -> (int) (l1.getCost() - l2.getCost()));
        return locationDTOS;
    }

    public List<LocationDTO> searchLocations(int startMonth, int endMonth, long toDays) {
        List<Location> locations;
        locations = locationRepository.userSearch(startMonth, endMonth);
        if(locations.isEmpty()){
            throw new ResourceNotFoundException("There is no location with this id!");
        }else {
            List<LocationDTO> locationDTOS = new ArrayList<>();
            double cost;
            for (Location location : locations) {
                cost = 0;
                for (LocationActivity locationActivity : location.getLocationActivities()) {
                    cost += locationActivity.getPrice();
                }
                LocationDTO locationDTO = modelMapper.map(location, LocationDTO.class);
                locationDTO.setCost(cost * toDays);
                locationDTOS.add(locationDTO);
            }
            locationDTOS.sort((l1, l2) -> (int) (l1.getCost() - l2.getCost()));
            return locationDTOS;
        }
    }

}

