package com.titanum.wanderwise.data.models;

public class Country {
    private int id;
    private String name;
    private String imageUrl;
    private boolean favorite;
    private String description;

    public Country() {
        // Default constructor required for Firestore
    }

    public Country(int id, String name, String imageUrl, boolean favorite, String description) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.favorite = favorite;
        this.description = description;
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

    public boolean isFavorite() {
        return favorite;
    }

    public String getDescription() {
        return description;
    }
}
