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
import com.titanum.wanderwise.data.models.Restaurant;
import com.titanum.wanderwise.ui.adapters.PlaceGridAdapter;
import com.titanum.wanderwise.ui.adapters.RestaurantGridAdapter;
import com.titanum.wanderwise.ui.viewmodels.PlacesListViewModel;
import com.titanum.wanderwise.ui.viewmodels.RestaurantListViewModel;


public class RestaurantListFragment extends Fragment {

    private GridView gridView;
    private RestaurantGridAdapter gridAdapter;
    private RestaurantListViewModel viewModel;

    private String countryId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        gridView = view.findViewById(R.id.gridview);
        gridAdapter = new RestaurantGridAdapter(getContext());
        gridView.setAdapter(gridAdapter);

        // Retrieve the countryId from arguments
        Bundle args = getArguments();
        if (args != null) {
            countryId = args.getString("countryId");
        }

        viewModel = new ViewModelProvider(this).get(RestaurantListViewModel.class);
        viewModel.getRestaurantList().observe(getViewLifecycleOwner(), restaurants -> {
            gridAdapter.setRestaurants(restaurants);
        });

        viewModel.fetchRestaurantsByCountry(getContext(), countryId);

        // Set click listener for grid items
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Restaurant selectedRestaurant = gridAdapter.getItem(position);
            if (selectedRestaurant != null) {
                Bundle bundle = new Bundle();
                bundle.putString("restaurantId", String.valueOf(selectedRestaurant.getId()));
                Navigation.findNavController(view1).navigate(R.id.action_restaurantListFragment_to_restaurantDetailsFragment, bundle);
            }
        });

        return view;
    }
}