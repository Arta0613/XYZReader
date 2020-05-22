package com.example.xyzreader.domain;

import androidx.annotation.NonNull;

public class Article {

    private final int id;
    @NonNull private final String title;
    @NonNull private final String author;
    @NonNull private final String body;
    @NonNull private final String thumbnail;
    @NonNull private final String coverImage;
    private final double aspectRatio;
    @NonNull private final String publishedDate;

    public Article(
            final int id,
            @NonNull final String title,
            @NonNull final String author,
            @NonNull final String body,
            @NonNull final String thumbnail,
            @NonNull final String coverImage,
            final double aspectRatio,
            @NonNull final String publishedDate
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.thumbnail = thumbnail;
        this.coverImage = coverImage;
        this.aspectRatio = aspectRatio;
        this.publishedDate = publishedDate;
    }

    public final int getId() {
        return id;
    }

    @NonNull
    public final String getTitle() {
        return title;
    }

    @NonNull
    public final String getAuthor() {
        return author;
    }

    @NonNull
    public final String getBody() {
        return body;
    }

    @NonNull
    public final String getThumbnail() {
        return thumbnail;
    }

    @NonNull
    public final String getCoverImage() {
        return coverImage;
    }

    public final double getAspectRatio() {
        return aspectRatio;
    }

    @NonNull
    public final String getPublishedDate() {
        return publishedDate;
    }

    @NonNull
    @Override
    public final String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", aspectRatio=" + aspectRatio +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
    }
}