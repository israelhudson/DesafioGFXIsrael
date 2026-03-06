package com.example.desafiogfxisrael.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;
import com.example.desafiogfxisrael.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private final ProductRepository repository;
    private final MutableLiveData<List<Product>> products = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);
    private List<Product> allProducts = new ArrayList<>();
    private boolean hasLoaded = false;

    public ProductViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public void loadProducts() {
        if (hasLoaded) {
            return;
        }

        loading.setValue(true);
        errorMessage.setValue(null);

        repository.fetchProducts(new ProductRepository.RepositoryCallback() {
            @Override
            public void onSuccess(List<Product> productsResponse) {
                hasLoaded = true;
                allProducts = new ArrayList<>(productsResponse);
                products.postValue(new ArrayList<>(productsResponse));
                loading.postValue(false);
            }

            @Override
            public void onError(String message) {
                errorMessage.postValue(message);
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

    public LiveData<String> getError() {
        return errorMessage;
    }
}
