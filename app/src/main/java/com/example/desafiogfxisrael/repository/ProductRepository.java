package com.example.desafiogfxisrael.repository;

import androidx.annotation.NonNull;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;
import com.example.desafiogfxisrael.model.ProductModel;
import com.example.desafiogfxisrael.network.ApiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    public interface RepositoryCallback {
        void onSuccess(List<Product> products);

        void onError(String message);
    }

    private final ApiService apiService;

    public ProductRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void fetchProducts(final RepositoryCallback callback) {
        apiService.getProducts().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductModel>> call, @NonNull Response<List<ProductModel>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    callback.onError("Erro ao carregar produtos");
                    return;
                }

                List<Product> products = new ArrayList<>();
                for (ProductModel productModel : response.body()) {
                    products.add(productModel.toDomain());
                }
                callback.onSuccess(products);
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductModel>> call, @NonNull Throwable throwable) {
                callback.onError(throwable.getMessage() == null ? "Erro de rede" : throwable.getMessage());
            }
        });
    }

    public List<Product> filterByCategory(List<Product> products, Category category) {
        if (products == null || products.isEmpty()) {
            return Collections.emptyList();
        }
        if (category == null) {
            return new ArrayList<>(products);
        }

        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
