package com.mycompany.locationmanagement.service;

import com.mycompany.locationmanagement.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationManagementServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationManagementServiceImpl locationManagementServiceImpl;

    @Test
    public void test_all_locations(){
        locationManagementServiceImpl.getAllLocations();
    }
}
