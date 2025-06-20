package com.hotel.gateway.dto;

public class HotelSearchCriteria {

    private String location;
    private int minPrice;
    private int maxPrice;
    private double minRating;

    // Default constructor
    public HotelSearchCriteria() {
    }

    // All-args constructor
    public HotelSearchCriteria(String location, int minPrice, int maxPrice, double minRating) {
        this.location = location;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minRating = minRating;
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    @Override
    public String toString() {
        return "HotelSearchCriteria{" +
                "location='" + location + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minRating=" + minRating +
                '}';
    }
}
