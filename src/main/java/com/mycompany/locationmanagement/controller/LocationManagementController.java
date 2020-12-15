package com.mycompany.locationmanagement.controller;

import com.mycompany.locationmanagement.entity.Location;
import com.mycompany.locationmanagement.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = null;
        try {
            locations = locationRepository.findAll();
            if (null != locations && locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationDetail(@PathVariable Long id){
        Optional<Location> locationOpt = null;
        try {
            locationOpt = locationRepository.findById(id);
            if (!locationOpt.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(locationOpt.get(), HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location){
        Location loc = null;
        try {
            loc = locationRepository.save(location);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loc, HttpStatus.CREATED);
    }

    @PutMapping("/locations")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location){
        Optional<Location> locationInDbOpt = null;
        Location location1 = null;
        try {
            locationInDbOpt = locationRepository.findById(location.getId());
            if (locationInDbOpt.isPresent()) {
                if (null != location.getLat()) {
                    locationInDbOpt.get().setLat(location.getLat());
                }
                if (null != location.getLng()) {
                    locationInDbOpt.get().setLng(location.getLng());
                }
                if (null != location.getType()) {
                    locationInDbOpt.get().setType(location.getType());
                }
                location1 = locationRepository.save(locationInDbOpt.get());
                return new ResponseEntity<>(location1, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id){
        Optional<Location> locationOpt = null;
        try {
            locationOpt = locationRepository.findById(id);
            if(locationOpt.isPresent()){
                locationRepository.deleteById(locationOpt.get().getId());
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(locationOpt.get(), HttpStatus.NO_CONTENT);
    }
}
