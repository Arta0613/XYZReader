package com.example.xyzreader.database;

import androidx.annotation.NonNull;

import com.example.xyzreader.domain.Article;
import com.example.xyzreader.domain.ArticleItem;

public class ArticleEntityMappers {

    @NonNull
    public final ArticleEntity mapArticleEntity(@NonNull final Article article) {
        return new ArticleEntity(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getBody(),
                article.getThumbnail(),
                article.getCoverImage(),
                article.getAspectRatio(),
                article.getPublishedDate()
        );
    }

    @NonNull
    public final ArticleItem mapArticleItem(@NonNull final ArticleEntity articleEntity) {
        return new ArticleItem(new Article(
                articleEntity.getId(),
                articleEntity.getTitle(),
                articleEntity.getAuthor(),
                articleEntity.getBody(),
                articleEntity.getThumbnail(),
                articleEntity.getCoverImage(),
                articleEntity.getAspectRatio(),
                articleEntity.getPublishedDate()
        ));
    }
}