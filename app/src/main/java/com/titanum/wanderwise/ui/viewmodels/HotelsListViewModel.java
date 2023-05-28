package com.titanum.wanderwise.ui.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelsListViewModel extends ViewModel {
    private MutableLiveData<List<Hotel>> hotelList;

    public LiveData<List<Hotel>> getHotelList() {
        if (hotelList == null) {
            hotelList = new MutableLiveData<>();
        }
        return hotelList;
    }

    public void fetchHotelsByCountry(Context context, String countryId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("hotels")
                .whereEqualTo("countryId", Integer.parseInt(countryId))
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        // Handle error
                        return;
                    }

                    List<Hotel> hotels = new ArrayList<>();
                    for (DocumentChange dc : querySnapshot.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            Hotel hotel = dc.getDocument().toObject(Hotel.class);
                            Log.d("Hotel Info", "Name: " + hotel );
                            hotels.add(hotel);
                        }
                    }

                    hotelList.setValue(hotels);

                });
    }
}
