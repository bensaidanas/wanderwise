package com.titanum.wanderwise.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.Hotel;
import com.titanum.wanderwise.data.models.Restaurant;

public class RestaurantDetailsViewModel extends ViewModel {
    private MutableLiveData<Restaurant> restaurantData;

    public LiveData<Restaurant> getRestaurantData(String restaurantId) {
        if (restaurantData == null) {
            restaurantData = new MutableLiveData<>();
            loadRestaurantData(restaurantId);
        }
        return restaurantData;
    }

    private void loadRestaurantData(String restaurantId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("restaurants")
                .document(restaurantId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Restaurant restaurant = document.toObject(Restaurant.class);
                            restaurantData.setValue(restaurant);
                        }
                    } else {
                        // Handle error
                    }
                });
    }
}
