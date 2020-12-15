package com.mycompany.locationmanagement.service;

import com.mycompany.locationmanagement.entity.Location;
import com.mycompany.locationmanagement.exception.BusinessException;
import com.mycompany.locationmanagement.exception.ErrorModel;
import com.mycompany.locationmanagement.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocationManagementServiceImpl implements LocationManagementService{

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> getLocationDetail(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    @Override
    public Location updateLocation(Location location) throws BusinessException {

        Optional<Location> locationInDbOpt = null;
        Location location1 = null;

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
        }else {
            ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND.value(), "Element with id: "+location.getId()+ " not found");
            throw new BusinessException(error);
        }
        return location1;
    }

    @Transactional
    @Override
    public Location deleteLocation(Long id) throws BusinessException {
        Optional<Location> locationOpt = null;
        locationOpt = locationRepository.findById(id);
        if(locationOpt.isPresent()){
            locationRepository.deleteById(locationOpt.get().getId());
        }else {
            ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND.value(), "Element with id: "+id+ " not found");
            throw new BusinessException(error);
        }
        return locationOpt.get();
    }
}
