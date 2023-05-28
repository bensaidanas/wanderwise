package com.titanum.wanderwise.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.titanum.wanderwise.R;
import com.titanum.wanderwise.databinding.FragmentLockScreenBinding;


public class LockScreenFragment extends Fragment {

    FragmentLockScreenBinding binding;

    public LockScreenFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLockScreenBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                Navigation.findNavController(buttonView).navigate(R.id.action_lockScreenFragment_to_countryListFragment);
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}