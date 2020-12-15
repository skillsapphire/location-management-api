package com.mycompany.locationmanagement.controller;

import com.mycompany.locationmanagement.entity.Location;
import com.mycompany.locationmanagement.exception.BusinessException;
import com.mycompany.locationmanagement.repository.LocationRepository;
import com.mycompany.locationmanagement.service.LocationManagementService;
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

    @Autowired
    private LocationManagementService locationManagementService;

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = null;
        locations = locationManagementService.getAllLocations();
        if (null != locations && locations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationDetail(@PathVariable Long id){
        Optional<Location> locationOpt = null;
        locationOpt = locationManagementService.getLocationDetail(id);
        if (!locationOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locationOpt.get(), HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location){
        Location loc = locationManagementService.createLocation(location);
        return new ResponseEntity<>(loc, HttpStatus.CREATED);
    }

    @PutMapping("/locations")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location) throws BusinessException {
        Location location1 = locationManagementService.updateLocation(location);
        return new ResponseEntity<>(location1, HttpStatus.OK);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id) throws BusinessException {
        Location location1 = locationManagementService.deleteLocation(id);
        return new ResponseEntity<>(location1, HttpStatus.NO_CONTENT);
    }
}
