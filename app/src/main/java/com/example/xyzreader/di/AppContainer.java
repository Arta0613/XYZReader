package com.example.xyzreader.di;

import androidx.annotation.NonNull;

import com.example.xyzreader.repository.ArticlesRepository;

public class AppContainer {

    @NonNull private final ArticlesRepository articlesRepository = new ArticlesRepository();

    @NonNull
    public ArticlesRepository getArticlesRepository() {
        return articlesRepository;
    }
}