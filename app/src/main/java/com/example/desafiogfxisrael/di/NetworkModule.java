package com.example.desafiogfxisrael.di;

import com.example.desafiogfxisrael.network.ApiService;
import com.example.desafiogfxisrael.network.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    static ApiService provideApiService() {
        return RetrofitClient.getInstance().create(ApiService.class);
    }
}
