package com.example.doangiuaki.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.doangiuaki.R;
import com.example.doangiuaki.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shoprepo {
    private MutableLiveData<List<Product>> mutabproductlist;

    public LiveData<List<Product>> getProduct() {
        if(mutabproductlist == null){
            mutabproductlist = new MutableLiveData<>();
            loadproduct();
        }
        return mutabproductlist;
    }

    private void loadproduct(){
        List<Product> productList = new ArrayList<>();

        //productList.add(new Product(UUID.randomUUID().toString(), "Boss Prefume", "san pham nuoc hoa den tu tay ban nha ",  ));
        productList.add(new Product(UUID.randomUUID().toString(), "Boss Prefume", "Nước hoa có thể được định nghĩa là những chất phát ra và khuếch tán mùi thơm dễ chịu Một sản phẩm nước hoa cao cấp năm trong bộ sưu tập mới đến từ tây ban nha ", 1200, true, R.drawable.picture_product_1));
        productList.add(new Product(UUID.randomUUID().toString(), "Victor&Rose Prefume", "Nước hoa có thể được định nghĩa là những chất phát ra và khuếch tán mùi thơm dễ chịu. Một sản phẩm nước hoa cao cấp năm trong bộ sưu tập mới đến từ bỉ", 1000, false, R.drawable.picture_product_2));
        productList.add(new Product(UUID.randomUUID().toString(), "Larhore Prefume", "Nước hoa có thể được định nghĩa là những chất phát ra và khuếch tán mùi thơm dễ chịu. Một sản phẩm nước hoa cao cấp năm trong bộ sưu tập mới đến từ thủ đô pháp", 1500, true, R.drawable.picture_product_3));
        productList.add(new Product(UUID.randomUUID().toString(), "Burbery Prefume", "Nước hoa có thể được định nghĩa là những chất phát ra và khuếch tán mùi thơm dễ chịu. Một sản phẩm nước hoa cao cấp năm trong bộ sưu tập mới đến từ brazil", 2200, true, R.drawable.picture_product_4));
        productList.add(new Product(UUID.randomUUID().toString(), "Boss Prefume limidted", "Nước hoa có thể được định nghĩa là những chất phát ra và khuếch tán mùi thơm dễ chịu. Một sản phẩm nước hoa cao cấp năm trong bộ sưu tập mới đến từ tây ban nha ", 2400, true, R.drawable.picture_product_1));
        productList.add(new Product(UUID.randomUUID().toString(), "Victor&Rose Prefume plus", "Nước hoa có thể được định nghĩa là những chất phát ra và khuếch tán mùi thơm dễ chịu. Một sản phẩm nước hoa cao cấp năm trong bộ sưu tập mới đến từ tây ban nha ", 1000, true, R.drawable.picture_product_2));


        mutabproductlist.setValue(productList);
    }
}
