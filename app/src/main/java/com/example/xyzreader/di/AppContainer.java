package com.example.xyzreader.di;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.xyzreader.repository.ArticlesRepository;

public class AppContainer {

    @NonNull private final ArticlesRepository articlesRepository;

    public AppContainer(@NonNull final Context context) {
        this.articlesRepository = new ArticlesRepository(context);
    }

    @NonNull
    public ArticlesRepository getArticlesRepository() {
        return articlesRepository;
    }
}