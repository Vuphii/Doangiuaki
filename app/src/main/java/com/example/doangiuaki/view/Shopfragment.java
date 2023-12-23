package com.example.doangiuaki.view;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.doangiuaki.R;
import com.example.doangiuaki.adapter.shopAdapter;
import com.example.doangiuaki.databinding.FragmentShopfragmentBinding;
import com.example.doangiuaki.models.Product;
import com.example.doangiuaki.viewmodels.Shopviewmodel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class Shopfragment extends Fragment implements shopAdapter.Shopinterface  {

    private static final String TAG = "Shopfragment";

    ViewFlipper viewflipper;
    Animation in, out;

    FragmentShopfragmentBinding fragmentShopfragmentBinding;
    private shopAdapter ShopAdapter;
    private Shopviewmodel shopviewmodel;
    private NavController navController;


    private ViewFlipper viewFlipper;
    public Shopfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentShopfragmentBinding = FragmentShopfragmentBinding.inflate(inflater, container, false);
        return fragmentShopfragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShopAdapter = new shopAdapter(this);
        fragmentShopfragmentBinding.recyclerview.setAdapter(ShopAdapter);
        shopviewmodel = new ViewModelProvider(requireActivity()).get(Shopviewmodel.class);
        shopviewmodel.getProduct().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> Products) {
                ShopAdapter.submitList(Products);
            }
        });

        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
        boolean isadded = shopviewmodel.addtoCart(product);
        if(isadded){
            Snackbar.make(requireView(), product.getName() + " added to cart.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_shopfragment_to_cartFragment);
                        }
                    })
                     .show();
        }else {
            Snackbar.make(requireView(), "Da dat toi da san pham duoc them vao gio hang.", Snackbar.LENGTH_LONG)
                    .show();

        }

    }

    @Override
    public void onItemclick(Product product) {
        Log.d(TAG, "onItemclick: "+ product.toString());
        shopviewmodel.setproduct(product);
        navController.navigate(R.id.action_shopfragment_to_productdetailFragment);

    }
}