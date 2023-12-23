package com.example.doangiuaki.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doangiuaki.R;
import com.example.doangiuaki.adapter.CartAdapter;
import com.example.doangiuaki.databinding.CartBinding;
import com.example.doangiuaki.models.CartItem;
import com.example.doangiuaki.viewmodels.Shopviewmodel;

import java.util.List;


public class CartFragment extends Fragment implements CartAdapter.CartInterFace {

    private static final String TAG = "CartFragment";
    Shopviewmodel shopviewmodel;
    CartBinding cartBinding;

    NavController navController;



    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        cartBinding = CartBinding.inflate(inflater, container, false);
        return cartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);

        CartAdapter cartAdapter = new CartAdapter(this);

        cartBinding.cartRecycleView.setAdapter(cartAdapter);
        cartBinding.cartRecycleView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopviewmodel = new ViewModelProvider(requireActivity()).get(Shopviewmodel.class);
        shopviewmodel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartAdapter.submitList(cartItems);
                cartBinding.buybutton.setEnabled(cartItems.size() > 0);


            }
        });

        shopviewmodel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                cartBinding.orderTotalView.setText("Total:_________________________" + aDouble.toString()+"$");

            }
        });

        cartBinding.buybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);

            }
        });

        cartBinding.imagebackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragment_to_shopfragment);
            }
        });
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        shopviewmodel.removefromCart(cartItem);

    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopviewmodel.changeQuantity(cartItem, quantity);
    }
}