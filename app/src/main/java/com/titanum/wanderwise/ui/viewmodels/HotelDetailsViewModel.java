package com.titanum.wanderwise.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.Hotel;
import com.titanum.wanderwise.data.models.PlaceToVisit;

public class HotelDetailsViewModel extends ViewModel {
    private MutableLiveData<Hotel> hotelData;

    public LiveData<Hotel> getHotelData(String hotelId) {
        if (hotelData == null) {
            hotelData = new MutableLiveData<>();
            loadHotelData(hotelId);
        }
        return hotelData;
    }

    private void loadHotelData(String hotelId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("hotels")
                .document(hotelId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Hotel hotel = document.toObject(Hotel.class);
                            hotelData.setValue(hotel);
                        }
                    } else {
                        // Handle error
                    }
                });
    }
}
