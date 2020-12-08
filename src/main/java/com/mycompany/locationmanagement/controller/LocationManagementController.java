package com.mycompany.locationmanagement.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {

    @GetMapping("/locations")
    public void getAllLocations(){
        System.out.println("locations");
    }

    @GetMapping("/locations/{id}")
    public void getLocationDetail(){
        System.out.println("locations/id");
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
