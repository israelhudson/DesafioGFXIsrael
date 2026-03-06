package com.example.desafiogfxisrael.di;

import com.example.desafiogfxisrael.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
