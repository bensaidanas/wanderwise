package com.titanum.wanderwise.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.titanum.wanderwise.R;
import com.titanum.wanderwise.data.models.PlaceToVisit;
import com.titanum.wanderwise.ui.adapters.PlaceGridAdapter;
import com.titanum.wanderwise.ui.viewmodels.PlacesListViewModel;

public class PlacesListFragment extends Fragment {

    private GridView gridView;
    private PlaceGridAdapter gridAdapter;
    private PlacesListViewModel viewModel;

    private String countryId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places_list, container, false);

        gridView = view.findViewById(R.id.gridview);
        gridAdapter = new PlaceGridAdapter(getContext());
        gridView.setAdapter(gridAdapter);

        // Retrieve the countryId from arguments
        Bundle args = getArguments();
        if (args != null) {
            countryId = args.getString("countryId");
        }

        viewModel = new ViewModelProvider(this).get(PlacesListViewModel.class);
        viewModel.getPlaceList().observe(getViewLifecycleOwner(), places -> {
            gridAdapter.setPlaces(places);
        });

        viewModel.fetchPlacesByCountry(getContext(), countryId);

        // Set click listener for grid items
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            PlaceToVisit selectedPlace = gridAdapter.getItem(position);
            if (selectedPlace != null) {
                Bundle bundle = new Bundle();
                bundle.putString("placeId", String.valueOf(selectedPlace.getId()));
                Navigation.findNavController(view1).navigate(R.id.action_placesListFragment_to_placeDetailsFragment, bundle);
            }
        });

        return view;
    }
}