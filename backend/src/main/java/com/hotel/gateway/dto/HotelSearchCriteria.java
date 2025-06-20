package com.hotel.gateway.repository;

import com.hotel.gateway.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Find cheapest hotels
    List<Hotel> findTop3ByOrderByPriceAsc();

    // Find top-rated hotels
    List<Hotel> findTop3ByOrderByRatingDesc();

    // Find by location
    List<Hotel> findByLocationContainingIgnoreCase(String location);

    // Find hotels matching custom search criteria
    @Query("SELECT h FROM Hotel h " +
           "WHERE (:location IS NULL OR LOWER(h.location) LIKE LOWER(CONCAT('%', :location, '%'))) " +
           "AND (h.price BETWEEN :minPrice AND :maxPrice) " +
           "AND (h.rating >= :minRating)")
    List<Hotel> findByFilters(
            @Param("location") String location,
            @Param("minPrice") int minPrice,
            @Param("maxPrice") int maxPrice,
            @Param("minRating") double minRating
    );
}
