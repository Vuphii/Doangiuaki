package com.example.doangiuaki.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doangiuaki.R;
import com.example.doangiuaki.adapter.CartAdapter;
import com.example.doangiuaki.databinding.OrderBinding;
import com.example.doangiuaki.models.CartItem;
import com.example.doangiuaki.viewmodels.Shopviewmodel;

import java.util.List;


public class OrderFragment extends Fragment implements CartAdapter.CartInterFace  {

    NavController navController;


    OrderBinding orderBinding;
    Shopviewmodel shopviewmodel;


    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        orderBinding = OrderBinding.inflate(inflater, container, false);
        return orderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        shopviewmodel = new ViewModelProvider(requireActivity()).get(Shopviewmodel.class);

        CartAdapter cartAdapter = new CartAdapter(this);

        orderBinding.OrderRecyclerView.setAdapter(cartAdapter);

        shopviewmodel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartAdapter.submitList(cartItems);

            }
        });

        shopviewmodel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                orderBinding.texttotalproduct.setText("Total Prices: " +aDouble.toString()+"$");

            }
        });

        orderBinding.imagebackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_orderFragment_to_cartFragment);
            }
        });

        orderBinding.continueshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopviewmodel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_thankFragment);
            }
        });
    }

    @Override
    public void deleteItem(CartItem cartItem) {

    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {

    }
}