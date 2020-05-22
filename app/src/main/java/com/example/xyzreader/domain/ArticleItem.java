package com.example.xyzreader.domain;

import androidx.annotation.NonNull;

public class ArticleItem {

    @NonNull private final Article article;

    public ArticleItem(@NonNull final Article article) {
        this.article = article;
    }

    @NonNull
    public final Article getArticle() {
        return article;
    }
}