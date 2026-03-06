package com.example.desafiogfxisrael.repository;

import androidx.annotation.NonNull;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;
import com.example.desafiogfxisrael.model.ProductModel;
import com.example.desafiogfxisrael.network.ApiService;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ProductRepository implements ProductRepositoryContract {
    private final ApiService apiService;

    @Inject
    public ProductRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchProducts(final RepositoryCallback callback) {
        apiService.getProducts().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductModel>> call, @NonNull Response<List<ProductModel>> response) {
                if (!response.isSuccessful()) {
                    callback.onError(response.code() >= 500 ? RepositoryError.SERVER_ERROR : RepositoryError.API_ERROR);
                    return;
                }
                if (response.body() == null || response.body().isEmpty()) {
                    callback.onError(RepositoryError.EMPTY_RESPONSE);
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
                callback.onError(mapThrowableToError(throwable));
            }
        });
    }

    @Override
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

    private RepositoryError mapThrowableToError(Throwable throwable) {
        if (throwable instanceof UnknownHostException
                || throwable instanceof ConnectException
                || throwable instanceof SocketTimeoutException
                || throwable instanceof IOException) {
            return RepositoryError.NO_INTERNET;
        }
        return RepositoryError.UNKNOWN;
    }
}
