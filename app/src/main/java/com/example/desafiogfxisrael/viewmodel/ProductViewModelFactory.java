package com.example.desafiogfxisrael.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.desafiogfxisrael.repository.ProductRepository;

public class ProductViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepository repository;

    public ProductViewModelFactory(ProductRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return modelClass.cast(new ProductViewModel(repository));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
