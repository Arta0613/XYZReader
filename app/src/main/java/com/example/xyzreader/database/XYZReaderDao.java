package com.example.xyzreader.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface XYZReaderDao {

    @Transaction
    @Query("SELECT * FROM ArticleEntity")
    LiveData<List<ArticleEntity>> getArticles();

    @Transaction
    @Insert(entity = ArticleEntity.class, onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertArticles(final List<ArticleEntity> articleEntities);
}