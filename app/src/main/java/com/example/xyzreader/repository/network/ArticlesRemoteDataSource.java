package com.example.xyzreader.repository.network;

import androidx.annotation.NonNull;

import com.example.xyzreader.data.ArticlesApiUseCase;
import com.example.xyzreader.domain.ArticleItem;

import java.util.List;

import io.reactivex.Single;

public class ArticlesRemoteDataSource {

    @NonNull private final ArticlesApiUseCase articlesApiUseCase = new ArticlesApiUseCase();

    @NonNull
    public final Single<List<ArticleItem>> getArticles() {
        return articlesApiUseCase.getArticles();
    }
}