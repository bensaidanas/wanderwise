package com.titanum.wanderwise.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.titanum.wanderwise.data.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryListViewModel extends ViewModel {
    private MutableLiveData<List<Country>> countryList;

    public LiveData<List<Country>> getCountryList() {
        if (countryList == null) {
            countryList = new MutableLiveData<>();
        }
        return countryList;
    }

    public void fetchCountries() {
        // Fetch countries from Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("countries")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Country> countries = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Country country = document.toObject(Country.class);
                            countries.add(country);
                        }
                        countryList.setValue(countries);
                    }
                });
    }
}
