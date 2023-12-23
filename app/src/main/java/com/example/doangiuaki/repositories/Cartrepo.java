package com.example.doangiuaki.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.doangiuaki.models.CartItem;
import com.example.doangiuaki.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Cartrepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutalTotalPrice = new MutableLiveData<Double>();
    public LiveData<List<CartItem>> getCart(){
        if(mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<>());
        caculateTotalPrice();

    }
    public boolean addtoCart(Product product) {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        List<CartItem> cartItemsList = new ArrayList<>(mutableCart.getValue());
        for(CartItem cartItem: cartItemsList){
            if(cartItem.getProduct().getId().equals(product.getId())){
                if(cartItem.getQuantity() == 5){
                    return false;
                }
                int index = cartItemsList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemsList.set(index, cartItem);
                mutableCart.setValue(cartItemsList);
                caculateTotalPrice();
                return true;
            }
        }

        CartItem cartItem = new CartItem(product, 1);
        cartItemsList.add(cartItem);
        mutableCart.setValue(cartItemsList);
        caculateTotalPrice();
        return true;
    }

    public void removefromCart(CartItem cartItem){
        if(mutableCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        caculateTotalPrice();
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        if(mutableCart.getValue() == null) return;

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CartItem updateItem = new CartItem(cartItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem), updateItem);

        mutableCart.setValue(cartItemList);
        caculateTotalPrice();
    }


    public void caculateTotalPrice(){
        if(mutableCart.getValue() == null) return;
        double total = 0.0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for(CartItem cartItem: cartItemList){
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        mutalTotalPrice.setValue(total);

    }
    public LiveData<Double> getTotalPrice(){
        if(mutalTotalPrice.getValue() == null) {
            mutalTotalPrice.setValue(0.0);
        }

        return mutalTotalPrice;
    }
}
