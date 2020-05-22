package com.example.xyzreader.repository;

import androidx.annotation.NonNull;

import com.example.xyzreader.domain.ArticleItem;
import com.example.xyzreader.repository.network.ArticlesRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class ArticlesRepository {

    @NonNull
    private final ArticlesRemoteDataSource remoteDataSource = new ArticlesRemoteDataSource();

    @NonNull
    public final Single<List<ArticleItem>> getArticles() {
        return remoteDataSource.getArticles();
    }
}