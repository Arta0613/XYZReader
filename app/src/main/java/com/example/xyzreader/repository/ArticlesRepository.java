package com.example.xyzreader.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.xyzreader.database.ArticleEntity;
import com.example.xyzreader.domain.ArticleItem;
import com.example.xyzreader.repository.local.ArticlesLocalDataSource;
import com.example.xyzreader.repository.network.ArticlesRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class ArticlesRepository {

    @NonNull private final ArticlesRemoteDataSource remoteDataSource;

    @NonNull private final ArticlesLocalDataSource localDataSource;

    private List<ArticleItem> articleItems;
    private ArticleItem currentArticleItem;
    private int initialPosition;

    public ArticlesRepository(@NonNull final Context context) {
        remoteDataSource = new ArticlesRemoteDataSource();
        localDataSource = new ArticlesLocalDataSource(context);
    }

    @NonNull
    public final Single<List<ArticleItem>> getFreshArticles() {
        return remoteDataSource.getArticles();
    }

    @NonNull
    public final LiveData<List<ArticleEntity>> getSavedArticles() {
        return localDataSource.getArticles();
    }

    @NonNull
    public final Single<List<Long>> saveArticles(
            @NonNull final List<ArticleEntity> articleEntities
    ) {
        return localDataSource.insertArticles(articleEntities);
    }

    public List<ArticleItem> getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(final List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    public ArticleItem getCurrentArticleItem() {
        return currentArticleItem;
    }

    public void setCurrentArticleItem(final ArticleItem currentArticleItem) {
        this.currentArticleItem = currentArticleItem;
    }

    public final int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(final int position) {
        this.initialPosition = position;
    }
}