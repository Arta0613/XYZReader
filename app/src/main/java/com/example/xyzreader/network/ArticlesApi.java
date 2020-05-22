package com.example.xyzreader.network;

import com.example.xyzreader.data.ArticlesApiResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ArticlesApi {

    @GET("xyz-reader.json")
    Observable<List<ArticlesApiResponse>> getArticles();
}