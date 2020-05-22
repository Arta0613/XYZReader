package com.example.xyzreader.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xyzreader.utils.StringHelper;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ArticlesApiResponse {

    @Nullable private final Integer id;
    @Nullable private final String title;
    @Nullable private final String author;
    @Nullable private final String body;
    @Nullable private final String thumb;
    @Nullable private final String photo;

    @Nullable
    @SerializedName("aspect_ratio")
    private final Double aspectRatio;

    @Nullable
    @SerializedName("published_date")
    private final String publishedDate;

    public ArticlesApiResponse(
            @Nullable final Integer id,
            @Nullable final String title,
            @Nullable final String author,
            @Nullable final String body,
            @Nullable final String thumb,
            @Nullable final String photo,
            @Nullable final Double aspectRatio,
            @Nullable final String publishedDate
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.thumb = thumb;
        this.photo = photo;
        this.aspectRatio = aspectRatio;
        this.publishedDate = publishedDate;
    }

    @NonNull
    public final Integer getId() {
        return id != null ? id : -1;
    }

    @NonNull
    public final String getTitle() {
        return getNonNullString(title);
    }

    @NonNull
    public final String getAuthor() {
        return getNonNullString(author);
    }

    @NonNull
    public final String getBody() {
        return getNonNullString(body);
    }

    @NonNull
    public final String getThumb() {
        return getNonNullString(thumb);
    }

    @NonNull
    public final String getPhoto() {
        return getNonNullString(photo);
    }

    @NonNull
    public final Double getAspectRatio() {
        return aspectRatio != null ? aspectRatio : -1;
    }

    @NonNull
    public final String getPublishedDate() {
        return getNonNullString(publishedDate);
    }

    @NonNull
    @Override
    public final String toString() {
        return "\nArticlesApiResponse{\n" +
                "\tid=" + id +
                ", \n\ttitle='" + title + '\'' +
                ", \n\tauthor='" + author + '\'' +
                ", \n\tbody='" + body + '\'' +
                ", \n\tthumb='" + thumb + '\'' +
                ", \n\tphoto='" + photo + '\'' +
                ", \n\taspectRatio=" + aspectRatio +
                ", \n\tpublishedDate='" + publishedDate + '\'' +
                "\n}";
    }

    @NonNull
    private String getNonNullString(@Nullable final String field) {
        return StringHelper.getOrReturnEmpty(field);
    }
}