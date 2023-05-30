package com.titanum.wanderwise.ui.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.Hotel;
import com.titanum.wanderwise.data.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListViewModel extends ViewModel {
    private MutableLiveData<List<Restaurant>> restaurantList;

    public LiveData<List<Restaurant>> getRestaurantList() {
        if (restaurantList == null) {
            restaurantList = new MutableLiveData<>();
        }
        return restaurantList;
    }

    public void fetchRestaurantsByCountry(Context context, String countryId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("restaurants")
                .whereEqualTo("countryId", Integer.parseInt(countryId))
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        // Handle error
                        return;
                    }

                    List<Restaurant> restaurants = new ArrayList<>();
                    for (DocumentChange dc : querySnapshot.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            Restaurant restaurant = dc.getDocument().toObject(Restaurant.class);

                            restaurants.add(restaurant);
                        }
                    }

                    restaurantList.setValue(restaurants);

                });
    }
}
