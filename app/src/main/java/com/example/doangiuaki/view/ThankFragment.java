package com.example.doangiuaki.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doangiuaki.R;
import com.example.doangiuaki.databinding.FragmentThankBinding;


public class ThankFragment extends Fragment {

    NavController navController;
    FragmentThankBinding fragmentThankBinding;


    public ThankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentThankBinding = FragmentThankBinding.inflate(inflater, container, false);
        return fragmentThankBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        fragmentThankBinding.buybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_thankFragment_to_shopfragment);
            }
        });
    }
}