package com.mycompany.locationmanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {

    @GetMapping("/locations")
    public void getAllLocations(){

    }

    @GetMapping("/locations/{id}")
    public void getLocationDetail(){

    }

    @PostMapping("/locations")
    public void createLocation(){

    }

    @PutMapping("/locations")
    public void updateLocation(){

    }

    @DeleteMapping("/locations")
    public void deleteLocation(){

    }
}
