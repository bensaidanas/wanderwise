package com.titanum.wanderwise.data.database;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.Country;
import com.titanum.wanderwise.data.models.Hotel;
import com.titanum.wanderwise.data.models.PlaceToVisit;
import com.titanum.wanderwise.data.models.Restaurant;

import java.util.List;

public class FirebaseFirestoreHelper {
    private FirebaseFirestore db;
    private CollectionReference countriesCollection;
    private CollectionReference hotelsCollection;
    private CollectionReference placesToVisitCollection;
    private CollectionReference restaurantsCollection;

    public FirebaseFirestoreHelper() {
        db = FirebaseFirestore.getInstance();
        countriesCollection = db.collection("countries");
        hotelsCollection = db.collection("hotels");
        placesToVisitCollection = db.collection("placesToVisit");
        restaurantsCollection = db.collection("restaurants");
    }

    public void addCountry(Country country) {
        String documentId = String.valueOf(country.getId());
        countriesCollection.document(documentId)
                .set(country)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Country added with ID: " + documentId))
                .addOnFailureListener(e -> Log.e("Firestore", "Error adding country", e));
    }

    public void addPlaceToVisit(PlaceToVisit placeToVisit) {
        String documentId = String.valueOf(placeToVisit.getId());
        placesToVisitCollection.document(documentId)
                .set(placeToVisit)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "PlaceToVisit added with ID: " + documentId))
                .addOnFailureListener(e -> Log.e("Firestore", "Error adding PlaceToVisit", e));
    }

    public void addRestaurant(Restaurant restaurant) {
        String documentId = String.valueOf(restaurant.getId());
        restaurantsCollection.document(documentId)
                .set(restaurant)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Restaurant added with ID: " + documentId))
                .addOnFailureListener(e -> Log.e("Firestore", "Error adding restaurant", e));
    }

    public void addHotel(Hotel hotel) {
        String documentId = String.valueOf(hotel.getId());
        hotelsCollection.document(documentId)
                .set(hotel)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Hotel added with ID: " + documentId))
                .addOnFailureListener(e -> Log.e("Firestore", "Error adding hotel", e));
    }

    public void getHotelsInCountry(int countryId) {
        hotelsCollection.whereEqualTo("countryId", countryId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Hotel> hotels = queryDocumentSnapshots.toObjects(Hotel.class);
                    for (Hotel hotel : hotels) {
                        Log.d("Hotel Info", "Name: " + hotel.getName() + ", ID: " + hotel.getId());
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error getting hotels in country", e));
    }

    public void getHotelById(String hotelId) {
        hotelsCollection.document(hotelId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Hotel hotel = documentSnapshot.toObject(Hotel.class);
                        Log.d("Hotel Info", "Name: " + hotel.getName() + ", Country ID: " + hotel.getCountryId());
                    } else {
                        Log.d("Hotel Info", "Hotel not found");
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error getting hotel by ID", e));
    }

    public void getPlacesToVisitInCountry(int countryId) {
        placesToVisitCollection.whereEqualTo("countryId", countryId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<PlaceToVisit> placesToVisit = queryDocumentSnapshots.toObjects(PlaceToVisit.class);
                    for (PlaceToVisit place : placesToVisit) {
                        Log.d("PlaceToVisit Info", "Name: " + place.getName() + ", ID: " + place.getId());
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error getting places to visit in country", e));
    }

    public void getPlaceToVisitById(String placeId) {
        placesToVisitCollection.document(placeId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        PlaceToVisit place = documentSnapshot.toObject(PlaceToVisit.class);
                        Log.d("PlaceToVisit Info", "Name: " + place.getName() + ", Country ID: " + place.getCountryId());
                    } else {
                        Log.d("PlaceToVisit Info", "PlaceToVisit not found");
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error getting place to visit by ID", e));
    }

    public void getRestaurantsInCountry(int countryId) {
        restaurantsCollection.whereEqualTo("countryId", countryId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Restaurant> restaurants = queryDocumentSnapshots.toObjects(Restaurant.class);
                    for (Restaurant restaurant : restaurants) {
                        Log.d("Restaurant Info", "Name: " + restaurant.getName() + ", ID: " + restaurant.getId());
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error getting restaurants in country", e));
    }

    public void getRestaurantById(String restaurantId) {
        restaurantsCollection.document(restaurantId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        Log.d("Restaurant Info", "Name: " + restaurant.getName() + ", Country ID: " + restaurant.getCountryId());
                    } else {
                        Log.d("Restaurant Info", "Restaurant not found");
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error getting restaurant by ID", e));
    }
}

