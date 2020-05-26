package com.example.xyzreader.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.xyzreader.repository.ArticlesRepository;
import com.example.xyzreader.ui.ArticleDetailViewModel;
import com.example.xyzreader.ui.ArticleListViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull private final ArticlesRepository repository;

    public ViewModelFactory(@NonNull final ArticlesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticleListViewModel.class)) {
            return (T) new ArticleListViewModel(repository);
        } else if (modelClass.isAssignableFrom(ArticleDetailViewModel.class)) {
            return (T) new ArticleDetailViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}