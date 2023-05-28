package com.titanum.wanderwise.data.models;

public class Restaurant {
    private int id;
    private int countryId;
    private String name;
    private String imageUrl;
    private String city;
    private String address;
    private double rating;
    private String cuisine;
    private String description;

    public Restaurant() {
        // Default constructor required for Firestore serialization
    }

    public Restaurant(int id, String name, int countryId, String imageUrl, String city, String address,
                      double rating, String cuisine, String description) {
        this.id = id;
        this.countryId = countryId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.cuisine = cuisine;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

