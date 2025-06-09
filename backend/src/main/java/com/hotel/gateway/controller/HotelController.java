package com.hotel.gateway.controller;

import com.hotel.gateway.model.Hotel;
import com.hotel.gateway.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Optional<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam String location) {
        return hotelService.searchByLocation(location);
    }

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}
