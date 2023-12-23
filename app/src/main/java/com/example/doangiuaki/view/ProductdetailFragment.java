package com.example.doangiuaki.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.doangiuaki.R;
import com.example.doangiuaki.adapter.shopAdapter;
import com.example.doangiuaki.databinding.ProductdetailBinding;
import com.example.doangiuaki.models.Product;
import com.example.doangiuaki.viewmodels.Shopviewmodel;
import com.google.android.material.snackbar.Snackbar;


public class ProductdetailFragment extends Fragment implements shopAdapter.Shopinterface {

    ProductdetailBinding productdetailBinding;
    Shopviewmodel shopviewmodel;
    private  shopAdapter ShopAdapter;


    NavController navController;





    public ProductdetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        productdetailBinding = ProductdetailBinding.inflate(inflater, container, false);
        return productdetailBinding.getRoot();

        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShopAdapter = new shopAdapter(this);

        navController = Navigation.findNavController(view);

        shopviewmodel = new ViewModelProvider(requireActivity()).get(Shopviewmodel.class);
        productdetailBinding.setShopviewmodel(shopviewmodel);
        productdetailBinding.imagebackbutton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_productdetailFragment_to_shopfragment);
            }
        });


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

    }
}