package com.titanum.wanderwise.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.titanum.wanderwise.R;
import com.titanum.wanderwise.data.models.Country;
import com.titanum.wanderwise.ui.adapters.CountryGridAdapter;
import com.titanum.wanderwise.ui.viewmodels.CountryListViewModel;

import java.util.List;

public class CountryListFragment extends Fragment {

    private GridView gridView;
    private CountryGridAdapter gridAdapter;
    private CountryListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);

        gridView = view.findViewById(R.id.gridview);
        gridAdapter = new CountryGridAdapter(getContext());
        gridView.setAdapter(gridAdapter);

        viewModel = new ViewModelProvider(this).get(CountryListViewModel.class);
        viewModel.getCountryList().observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                gridAdapter.setCountries(countries);
            }
        });

        viewModel.fetchCountries();

        // Set click listener for grid items
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Country selectedCountry = gridAdapter.getItem(position);
            if (selectedCountry != null) {
                Bundle bundle = new Bundle();
                bundle.putString("countryId", String.valueOf(selectedCountry.getId()));
                Navigation.findNavController(view1).navigate(R.id.action_countryListFragment_to_countryDetailsFragment, bundle);
            }
        });

        return view;
    }
}