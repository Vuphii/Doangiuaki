package com.example.doangiuaki.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doangiuaki.databinding.ShowRowBinding;
import com.example.doangiuaki.models.Product;

public class shopAdapter extends ListAdapter<Product, shopAdapter.shopviewHolder> {

    Shopinterface shopinterface;

    public shopAdapter(Shopinterface shopinterface) {
        super(Product.itemCallback);
        this.shopinterface = shopinterface;
    }

    @NonNull
    @Override
    public shopviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShowRowBinding showRowBinding = ShowRowBinding.inflate(layoutInflater, parent, false);
        showRowBinding.setShopInterface(shopinterface);
        return new shopviewHolder(showRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull shopviewHolder holder, int position) {
        Product product = getItem(position);
        holder.showRowBinding.setProduct(product);
        holder.showRowBinding.executePendingBindings();

    }

    class shopviewHolder extends RecyclerView.ViewHolder{

        ShowRowBinding showRowBinding;

        public shopviewHolder(ShowRowBinding binding)
        {
            super(binding.getRoot());
            this.showRowBinding = binding;
        }
    }

    public interface Shopinterface{
        void addItem(Product product);
        void onItemclick(Product product);
    }

}
