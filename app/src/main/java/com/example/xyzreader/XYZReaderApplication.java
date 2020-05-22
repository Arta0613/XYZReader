package com.example.xyzreader;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.xyzreader.di.AppContainer;

public class XYZReaderApplication extends Application {

    @NonNull private final AppContainer appContainer = new AppContainer();

    @NonNull
    public final AppContainer getAppContainer() {
        return appContainer;
    }
}