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
import com.titanum.wanderwise.data.models.Hotel;
import com.titanum.wanderwise.data.models.PlaceToVisit;
import com.titanum.wanderwise.ui.adapters.HotelGridAdapter;
import com.titanum.wanderwise.ui.viewmodels.HotelsListViewModel;


public class HotelListFragment extends Fragment {

    private GridView gridView;
    private HotelGridAdapter gridAdapter;
    private HotelsListViewModel viewModel;

    private String countryId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel_list, container, false);

        gridView = view.findViewById(R.id.gridview);
        gridAdapter = new HotelGridAdapter(getContext());
        gridView.setAdapter(gridAdapter);

        // Retrieve the countryId from arguments
        Bundle args = getArguments();
        if (args != null) {
            countryId = args.getString("countryId");
        }

        viewModel = new ViewModelProvider(this).get(HotelsListViewModel.class);
        viewModel.getHotelList().observe(getViewLifecycleOwner(), hotels -> {
            gridAdapter.setHotels(hotels);
        });

        viewModel.fetchHotelsByCountry(getContext(), countryId);

        // Set click listener for grid items
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Hotel hotel = gridAdapter.getItem(position);
            if (hotel != null) {
                Bundle bundle = new Bundle();
                bundle.putString("hotelId", String.valueOf(hotel.getId()));
                Navigation.findNavController(view1).navigate(R.id.action_hotelListFragment_to_hotelDetailsFragment, bundle);
            }
        });

        return view;
    }
}