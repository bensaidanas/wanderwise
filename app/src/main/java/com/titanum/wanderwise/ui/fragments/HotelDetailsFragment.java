package com.titanum.wanderwise.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.titanum.wanderwise.R;
import com.titanum.wanderwise.databinding.FragmentHotelDetailsBinding;
import com.titanum.wanderwise.databinding.FragmentPlaceDetailsBinding;
import com.titanum.wanderwise.ui.viewmodels.HotelDetailsViewModel;
import com.titanum.wanderwise.ui.viewmodels.PlaceDetailsViewModel;


public class HotelDetailsFragment extends Fragment {

    private FragmentHotelDetailsBinding binding;
    private HotelDetailsViewModel viewModel;

    public HotelDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHotelDetailsBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(HotelDetailsViewModel.class);

        // Retrieve data from arguments
        Bundle args = getArguments();
        if (args != null) {
            String hotelId = args.getString("hotelId");
            if (hotelId != null) {
                viewModel.getHotelData(hotelId).observe(getViewLifecycleOwner(), place -> {
                    if (place != null) {
                        // Set the title, image, and description
                        binding.titleTextView.setText(place.getName());
                        Glide.with(requireContext())
                                .load(place.getImageUrl())
                                .into(binding.imageView);
                        binding.descriptionTextView.setText(place.getDescription());
                    }
                });
            }
        }
        // Show the action bar
        ((AppCompatActivity) requireActivity()).getSupportActionBar().show();

        return rootView;
    }
}