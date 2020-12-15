package com.mycompany.locationmanagement.service;

import com.mycompany.locationmanagement.entity.Location;
import com.mycompany.locationmanagement.exception.BusinessException;

import java.util.List;
import java.util.Optional;

public interface LocationManagementService {

    public List<Location> getAllLocations();
    public Optional<Location> getLocationDetail(Long id);
    public Location createLocation(Location location);
    public Location updateLocation(Location location) throws BusinessException;
    public Location deleteLocation(Long id) throws BusinessException;

}
