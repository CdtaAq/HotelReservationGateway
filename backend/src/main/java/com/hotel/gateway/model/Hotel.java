package com.hotel.gateway.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double price;
    private double rating;
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amenities_id")
    private Amenities amenities;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    // Constructors
    public Hotel() {}

    public Hotel(String name, String location, double price, double rating, String imageUrl) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
