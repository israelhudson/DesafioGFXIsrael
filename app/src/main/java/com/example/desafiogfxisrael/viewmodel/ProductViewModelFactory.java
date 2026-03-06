package com.example.desafiogfxisrael.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.desafiogfxisrael.repository.ProductRepositoryContract;

import javax.inject.Inject;

public class ProductViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepositoryContract repository;

    @Inject
    public ProductViewModelFactory(ProductRepositoryContract repository) {
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
