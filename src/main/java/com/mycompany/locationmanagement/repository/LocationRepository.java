package com.mycompany.locationmanagement.repository;

import com.mycompany.locationmanagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
