package com.mycompany.locationmanagement.controller;

import com.mycompany.locationmanagement.entity.Location;
import com.mycompany.locationmanagement.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public List<Location> getAllLocations(){
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

    @GetMapping("/locations/{id}")
    public Location getLocationDetail(@PathVariable Long id){
        System.out.println("locations/id");
        Optional<Location> locationOpt = locationRepository.findById(id);
        if(locationOpt.isPresent()){
            return locationOpt.get();
        }
        return null;
    }

    @PostMapping("/locations")
    public void createLocation(@RequestBody Location location){
        locationRepository.save(location);
    }

    @PutMapping("/locations")
    public void updateLocation(@RequestBody Location location){
        Optional<Location> locationInDbOpt = locationRepository.findById(location.getId());
        if(locationInDbOpt.isPresent()){
            if(null != location.getLat()){
                locationInDbOpt.get().setLat(location.getLat());
            }
            if(null != location.getLng()){
                locationInDbOpt.get().setLng(location.getLng());
            }
            if(null != location.getType()){
                locationInDbOpt.get().setType(location.getType());
            }
            locationRepository.save(locationInDbOpt.get());
        }
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable Long id){
        locationRepository.deleteById(id);
    }
}
