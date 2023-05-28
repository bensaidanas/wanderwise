package com.titanum.wanderwise.data.models;

public class Hotel {
    private int id;
    private String name;
    private String imageUrl;
    private String city;
    private String address;
    private double rating;
    private double pricePerNight;
    private String description;
    private int countryId;

    public Hotel() {
        // Default constructor required for Firestore
    }

    public Hotel(int id, String name, String imageUrl, String city, String address, double rating,
                 double pricePerNight, String description, int countryId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public int getCountryId() {
        return countryId;
    }
}