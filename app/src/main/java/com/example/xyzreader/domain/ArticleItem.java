package com.example.xyzreader.domain;

import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT;

public class ArticleItem {

    @NonNull private final Article article;
    @NonNull private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss", Locale.getDefault());
    @NonNull private final SimpleDateFormat outputFormat = new SimpleDateFormat();
    @NonNull private final GregorianCalendar START_OF_EPOCH = new GregorianCalendar(2, 1, 1);

    public ArticleItem(@NonNull final Article article) {
        this.article = article;
    }

    @NonNull
    public final String getTitle() {
        return article.getTitle();
    }

    @NonNull
    public final String getSubtitle() {
        final Date publishedDate = parsePublishedDate();

        if (!publishedDate.before(START_OF_EPOCH.getTime())) {
            return DateUtils.getRelativeTimeSpanString(
                    publishedDate.getTime(), System.currentTimeMillis(),
                    DateUtils.HOUR_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL
            ).toString() + "\nby " + article.getAuthor();
        } else {
            return outputFormat.format(publishedDate) + "\nby " + article.getAuthor();
        }
    }

    @NonNull
    public final String getThumbnail() {
        return article.getThumbnail();
    }

    public final double getAspectRatio() {
        return article.getAspectRatio();
    }

    @NonNull
    private Date parsePublishedDate() {
        try {
            return Objects.requireNonNull(dateFormat.parse(article.getPublishedDate()));
        } catch (ParseException | NullPointerException e) {
            Log.e(ArticleItem.class.getSimpleName(), "Error parsing publish date: ", e);
            return new Date();
        }
    }

    @NonNull
    public final Article getArticle() {
        return article;
    }

    @NonNull
    public final String getBody() {
        return htmlFormattedBody(article.getBody());
    }

    @NonNull
    public final String getSampleBody() {
        return htmlFormattedBody(article.getBody().substring(0, 1000));
    }

    @NonNull
    public String getCover() {
        return article.getCoverImage();
    }

    @NonNull
    private String htmlFormattedBody(@NonNull final String body) {
        return Html.fromHtml(body.replaceAll("(\r\n|\n)", "<br />"), FROM_HTML_MODE_COMPACT).toString();
    }
}