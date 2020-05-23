package com.example.xyzreader;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.xyzreader.di.AppContainer;

public class XYZReaderApplication extends Application {

    private AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(getApplicationContext());
    }

    @NonNull
    public final AppContainer getAppContainer() {
        return appContainer;
    }
}