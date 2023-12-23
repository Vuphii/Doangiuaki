package com.example.doangiuaki.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.example.doangiuaki.R;

import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private String mota;
    private double price;
    private boolean catogory;
    private int images;


    public Product(String id, String name, String mota, double price, boolean catogory, int images) {
        this.id = id;
        this.name = name;
        this.mota = mota;
        this.price = price;
        this.catogory = catogory;
        this.images = images;
    }

    /*public Product(String id, String name, double price, boolean catogory, int images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.catogory = catogory;
        this.images = images;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isCatogory() {
        return catogory;
    }

    public void setCatogory(boolean catogory) {
        this.catogory = catogory;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mota='" + mota + '\'' +
                ", price=" + price +
                ", catogory=" + catogory +
                ", images=" + images +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 && isCatogory() == product.isCatogory() && getImages() == product.getImages() && getId().equals(product.getId()) && Objects.equals(getName(), product.getName()) && getMota().equals(product.getMota());
    }

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };



    /*@Override
       public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Double.compare(product.getPrice(), getPrice()) == 0 && isCatogory() == product.isCatogory() && getImages() == product.getImages() && getId().equals(product.getId()) && getName().equals(product.getName());
        }


        public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.equals(newItem);
            }
        };*/
    @BindingAdapter("android:resoure")
    public static void loadimage(ImageView imageView, int images){
        imageView.setImageResource(images);
    }
}
