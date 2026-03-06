package com.example.desafiogfxisrael;

import android.app.Application;

import com.example.desafiogfxisrael.di.AppComponent;
import com.example.desafiogfxisrael.di.DaggerAppComponent;

public class DesafioApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
