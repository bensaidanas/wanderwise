package com.titanum.wanderwise.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.titanum.wanderwise.R;
import com.titanum.wanderwise.databinding.FragmentCountryDetailsBinding;
import com.titanum.wanderwise.ui.viewmodels.CountryDetailsViewModel;


public class CountryDetailsFragment extends Fragment {

    private FragmentCountryDetailsBinding binding;
    private CountryDetailsViewModel viewModel;

    public CountryDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(CountryDetailsViewModel.class);

        // Retrieve data from arguments
        Bundle args = getArguments();
        if (args != null) {
            String countryId = args.getString("countryId");
            if (countryId != null) {
                viewModel.getCountryData(countryId).observe(getViewLifecycleOwner(), country -> {
                    if (country != null) {
                        // Set the title, image, and description
                        binding.titleTextView.setText(country.getName());
                        Glide.with(requireContext())
                                .load(country.getImageUrl())
                                .into(binding.imageView);
                        binding.descriptionTextView.setText(country.getDescription());
                    }
                });
            }
        }

        // Set click listener for "Hotels" button
        binding.hotelsButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("countryId", args.getString("countryId"));
            Log.d("Country Info", "Country Id: " + args.getString("countryId"));
            Navigation.findNavController(rootView).navigate(R.id.action_countryDetailsFragment_to_hotelListFragment, bundle);
        });

        // Set click listener for "Places" button
        binding.placesButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("countryId", args.getString("countryId"));
            Navigation.findNavController(rootView).navigate(R.id.action_countryDetailsFragment_to_placesListFragment, bundle);
        });

        // Set click listener for "Restaurant" button
        binding.restaurantsButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("countryId", args.getString("countryId"));
            Navigation.findNavController(rootView).navigate(R.id.action_countryDetailsFragment_to_restaurantListFragment, bundle);
        });

        // Show the action bar
        ((AppCompatActivity) requireActivity()).getSupportActionBar().show();

        return rootView;
    }
}