package com.example.desafiogfxisrael.di;

import com.example.desafiogfxisrael.repository.ProductRepository;
import com.example.desafiogfxisrael.repository.ProductRepositoryContract;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract ProductRepositoryContract bindProductRepository(ProductRepository productRepository);
}
