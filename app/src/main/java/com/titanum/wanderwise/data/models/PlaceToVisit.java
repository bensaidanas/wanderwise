package com.titanum.wanderwise.data.models;

public class PlaceToVisit {
    private int id;
    private String name;
    private int countryId;
    private String imageUrl;
    private String city;
    private String address;
    private double rating;
    private boolean isVisited;
    private String description;

    public PlaceToVisit() {
        // Default constructor required for Firestore serialization
    }

    public PlaceToVisit(int id, String name, int countryId, String imageUrl, String city, String address,
                        double rating, boolean isVisited, String description) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.imageUrl = imageUrl;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.isVisited = isVisited;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

}

