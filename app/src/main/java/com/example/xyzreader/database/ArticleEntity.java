package com.example.xyzreader.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArticleEntity {

    @NonNull
    @PrimaryKey
    private final Integer id;

    @NonNull private final String title;
    @NonNull private final String author;
    @NonNull private final String body;
    @NonNull private final String thumbnail;
    @NonNull private final String coverImage;
    @NonNull private final Double aspectRatio;
    @NonNull private final String publishedDate;

    public ArticleEntity(
            @NonNull final Integer id,
            @NonNull final String title,
            @NonNull final String author,
            @NonNull final String body,
            @NonNull final String thumbnail,
            @NonNull final String coverImage,
            @NonNull final Double aspectRatio,
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

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    @NonNull
    public String getThumbnail() {
        return thumbnail;
    }

    @NonNull
    public String getCoverImage() {
        return coverImage;
    }

    @NonNull
    public Double getAspectRatio() {
        return aspectRatio;
    }

    @NonNull
    public String getPublishedDate() {
        return publishedDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "ArticleEntity{" +
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