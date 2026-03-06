package com.example.desafiogfxisrael.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;
import com.example.desafiogfxisrael.repository.ProductRepositoryContract;
import com.example.desafiogfxisrael.repository.RepositoryError;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private final ProductRepositoryContract repository;
    private final MutableLiveData<List<Product>> products = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<RepositoryError> errorMessage = new MutableLiveData<>(null);
    private List<Product> allProducts = new ArrayList<>();
    private boolean hasLoaded = false;

    public ProductViewModel(ProductRepositoryContract repository) {
        this.repository = repository;
    }

    public void loadProducts() {
        if (hasLoaded) {
            return;
        }

        loading.setValue(true);
        errorMessage.setValue(null);

        repository.fetchProducts(new ProductRepositoryContract.RepositoryCallback() {
            @Override
            public void onSuccess(List<Product> productsResponse) {
                hasLoaded = true;
                allProducts = new ArrayList<>(productsResponse);
                products.postValue(new ArrayList<>(productsResponse));
                loading.postValue(false);
            }

            @Override
            public void onError(RepositoryError error) {
                errorMessage.postValue(error);
                loading.postValue(false);
            }
        });
    }

    public void applyCategoryFilter(Category category) {
        List<Product> filtered = repository.filterByCategory(allProducts, category);
        products.setValue(filtered);
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<RepositoryError> getError() {
        return errorMessage;
    }
}
