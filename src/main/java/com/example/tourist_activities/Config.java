package com.example.tourist_activities;

import com.example.tourist_activities.model.Activity;
import com.example.tourist_activities.model.Location;
import com.example.tourist_activities.model.LocationActivity;
import com.example.tourist_activities.model.LocationDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        Converter<List<LocationActivity>, String[]> convertActivityToString =
                ctx -> ctx.getSource()
                        .stream()
                        .map(LocationActivity::getActivity)
                        .map(Activity::getActivityName)
                        .toArray(String[]::new);


        modelMapper.createTypeMap(Location.class, LocationDTO.class)
                .addMappings(map -> map
                        .using(convertActivityToString)
                        .map(
                                Location::getLocationActivities,
                                LocationDTO::setActivities
                        )
                );
        return modelMapper;
    }
}
