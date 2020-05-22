package com.example.xyzreader.domain;

import androidx.annotation.NonNull;

import com.example.xyzreader.data.ArticlesApiResponse;

public class ArticleMapper {

    @NonNull
    public final Article mapArticle(@NonNull final ArticlesApiResponse articlesApiResponse) {
        return new Article(
                articlesApiResponse.getId(),
                articlesApiResponse.getTitle(),
                articlesApiResponse.getAuthor(),
                articlesApiResponse.getBody(),
                articlesApiResponse.getThumb(),
                articlesApiResponse.getPhoto(),
                articlesApiResponse.getAspectRatio(),
                articlesApiResponse.getPublishedDate()
        );
    }

    @NonNull
    public final ArticleItem mapArticleItem(@NonNull final Article article) {
        return new ArticleItem(article);
    }
}