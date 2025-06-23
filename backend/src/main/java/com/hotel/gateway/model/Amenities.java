package com.hotel.gateway.model;

import jakarta.persistence.*;

@Entity
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean wifi;
    private boolean pool;
    private boolean parking;
    private boolean breakfast;
    private boolean airConditioning;

    @OneToOne(mappedBy = "amenities")
    private Hotel hotel;

    // Constructors
    public Amenities() {}

    public Amenities(boolean wifi, boolean pool, boolean parking, boolean breakfast, boolean airConditioning) {
        this.wifi = wifi;
        this.pool = pool;
        this.parking = parking;
        this.breakfast = breakfast;
        this.airConditioning = airConditioning;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
