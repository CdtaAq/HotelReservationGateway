package com.hotel.gateway.service;

import com.hotel.gateway.model.Hotel;
import com.hotel.gateway.model.Amenities;
import com.hotel.gateway.model.HotelSearchCriteria;
import com.hotel.gateway.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> searchHotels(HotelSearchCriteria criteria) {
        return hotelRepository.findByLocationContainingIgnoreCaseAndPriceBetweenAndRatingGreaterThanEqual(
                criteria.getLocation(),
                criteria.getMinPrice(),
                criteria.getMaxPrice(),
                criteria.getMinRating()
        );
    }

    public List<Hotel> getHotelsByAmenity(String amenity) {
        return hotelRepository.findByAmenitiesNameContainingIgnoreCase(amenity);
    }

    // 
    public Set<String> getAllAmenities() {
        return hotelRepository.findAll().stream()
                .flatMap(hotel -> hotel.getAmenities().stream())
                .map(Amenities::getName)
                .collect(Collectors.toSet());
    }

    // 
    public List<Amenities> getAmenitiesByHotelId(Long hotelId) {
        Hotel hotel = getHotelById(hotelId);
        return hotel != null ? hotel.getAmenities() : Collections.emptyList();
    }
}
