package com.titanum.wanderwise.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.PlaceToVisit;

public class PlaceDetailsViewModel extends ViewModel {
    private MutableLiveData<PlaceToVisit> placeData;

    public LiveData<PlaceToVisit> getPlaceData(String placeId) {
        if (placeData == null) {
            placeData = new MutableLiveData<>();
            loadPlaceData(placeId);
        }
        return placeData;
    }

    private void loadPlaceData(String placeId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("placesToVisit")
                .document(placeId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            PlaceToVisit place = document.toObject(PlaceToVisit.class);
                            placeData.setValue(place);
                        }
                    } else {
                        // Handle error
                    }
                });
    }
}
