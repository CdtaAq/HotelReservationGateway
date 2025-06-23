package com.hotel.gateway.repository;

import com.hotel.gateway.model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
}
