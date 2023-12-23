package com.example.doangiuaki.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doangiuaki.databinding.CartRowBinding;
import com.example.doangiuaki.models.CartItem;

public class CartAdapter extends ListAdapter<CartItem, CartAdapter.CartViewHolder> {
    private CartInterFace cartInterFace;
    public CartAdapter(CartInterFace cartInterFace) {
        super(CartItem.itemCallback);
        this.cartInterFace = cartInterFace;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();

    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        CartRowBinding cartRowBinding;

        public CartViewHolder(CartRowBinding binding) {
            super(binding.getRoot());
            this.cartRowBinding = binding;
            binding.deletepbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartInterFace.deleteItem(getItem(getBindingAdapterPosition()));
                }
            });

            binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int quantity = position +1;
                    if(quantity == getItem(getBindingAdapterPosition()).getQuantity()){
                        return;
                    }
                    cartInterFace.changeQuantity(getItem(getBindingAdapterPosition()), quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

        public interface CartInterFace{
            void deleteItem(CartItem cartItem);
            void changeQuantity(CartItem cartItem, int quantity);
        }
}
