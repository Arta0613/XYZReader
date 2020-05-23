package com.example.xyzreader.repository.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.xyzreader.database.ArticleEntity;
import com.example.xyzreader.database.XYZReaderDao;
import com.example.xyzreader.database.XYZReaderDatabase;

import java.util.List;

import io.reactivex.Single;

public class ArticlesLocalDataSource {

    @NonNull private final XYZReaderDao dao;

    public ArticlesLocalDataSource(@NonNull final Context context) {
        this.dao = XYZReaderDatabase.getInstance(context).xyzReaderDao();
    }

    @NonNull
    public LiveData<List<ArticleEntity>> getArticles() {
        return dao.getArticles();
    }

    @NonNull
    public Single<List<Long>> insertArticles(@NonNull final List<ArticleEntity> articleEntities) {
        return dao.insertArticles(articleEntities);
    }
}