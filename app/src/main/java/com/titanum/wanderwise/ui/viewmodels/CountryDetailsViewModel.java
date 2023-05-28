package com.titanum.wanderwise.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.titanum.wanderwise.data.models.Country;

public class CountryDetailsViewModel extends ViewModel {
    private MutableLiveData<Country> countryData;

    public LiveData<Country> getCountryData(String countryId) {
        if (countryData == null) {
            countryData = new MutableLiveData<>();
            loadCountryData(countryId);
        }
        return countryData;
    }

    private void loadCountryData(String countryId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("countries")
                .document(countryId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Country country = document.toObject(Country.class);
                            countryData.setValue(country);
                        }
                    } else {
                        // Handle error
                    }
                });
    }
}
