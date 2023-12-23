package com.example.doangiuaki.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doangiuaki.models.CartItem;
import com.example.doangiuaki.models.Product;
import com.example.doangiuaki.repositories.Cartrepo;
import com.example.doangiuaki.repositories.Shoprepo;

import java.util.List;



public class Shopviewmodel extends ViewModel {

    Shoprepo shoprepo = new Shoprepo();
    Cartrepo cartrepo = new Cartrepo();

    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();
    public LiveData<List<Product>> getProduct(){
        return shoprepo.getProduct();
    }

    public void setproduct(Product product){
        mutableProduct.setValue(product);

    }

    public  LiveData<Product> getProducts(){
        return mutableProduct;

    }

    public LiveData<List<CartItem>>getCart(){
        return cartrepo.getCart();
    }

    public boolean addtoCart(Product product){
        return cartrepo.addtoCart(product);
    }

    public void removefromCart(CartItem cartItem){
         cartrepo.removefromCart(cartItem);
    }


    public void changeQuantity(CartItem cartItem, int quantity) {
        cartrepo.changeQuantity(cartItem, quantity);
    }

    public LiveData<Double> getTotalPrice(){
        return cartrepo.getTotalPrice();
    }

    public void resetCart(){
        cartrepo.initCart();
    }
}
