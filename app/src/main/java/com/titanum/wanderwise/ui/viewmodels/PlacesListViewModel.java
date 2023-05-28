package com.titanum.wanderwise.ui.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.PlaceToVisit;

import java.util.ArrayList;
import java.util.List;

public class PlacesListViewModel extends ViewModel {
    private MutableLiveData<List<PlaceToVisit>> placeList;

    public LiveData<List<PlaceToVisit>> getPlaceList() {
        if (placeList == null) {
            placeList = new MutableLiveData<>();
        }
        return placeList;
    }

    public void fetchPlacesByCountry(Context context, String countryId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("placesToVisit")
                .whereEqualTo("countryId", Integer.parseInt(countryId))
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        // Handle error
                        return;
                    }

                    List<PlaceToVisit> places = new ArrayList<>();
                    for (DocumentChange dc : querySnapshot.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            PlaceToVisit place = dc.getDocument().toObject(PlaceToVisit.class);
                            places.add(place);
                        }
                    }

                    placeList.setValue(places);

                });
    }
}
