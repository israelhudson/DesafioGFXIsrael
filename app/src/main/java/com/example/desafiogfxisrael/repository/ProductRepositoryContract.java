package com.example.desafiogfxisrael.repository;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;

import java.util.List;

public interface ProductRepositoryContract {
    interface RepositoryCallback {
        void onSuccess(List<Product> products);

        void onError(RepositoryError error);
    }

    void fetchProducts(RepositoryCallback callback);

    List<Product> filterByCategory(List<Product> products, Category category);
}
