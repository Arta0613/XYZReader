package com.example.xyzreader.data;

import androidx.annotation.NonNull;

import com.example.xyzreader.domain.ArticleItem;
import com.example.xyzreader.domain.ArticleMapper;
import com.example.xyzreader.network.ArticlesApi;
import com.example.xyzreader.network.ArticlesServiceGenerator;

import java.util.List;

import io.reactivex.Single;

public class ArticlesApiUseCase {

    @NonNull private final ArticleMapper articleMapper = new ArticleMapper();

    @NonNull private final ArticlesApi articlesApi =
            new ArticlesServiceGenerator().createArticlesApiService(ArticlesApi.class);

    @NonNull
    public final Single<List<ArticleItem>> getArticles() {
        return articlesApi.getArticles()
                .flatMapIterable(articlesApiResponses -> articlesApiResponses)
                .map(articleMapper::mapArticle)
                .map(articleMapper::mapArticleItem)
                .toList();
    }
}