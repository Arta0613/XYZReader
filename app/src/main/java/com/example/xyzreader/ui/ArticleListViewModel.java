package com.example.xyzreader.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.xyzreader.domain.ArticleItem;
import com.example.xyzreader.repository.ArticlesRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class ArticleListViewModel extends ViewModel {

    @NonNull private final ArticlesRepository repository;
    @NonNull private final CompositeDisposable disposable = new CompositeDisposable();
    @NonNull private final ArticlesAdapter articlesAdapter = new ArticlesAdapter();

    public final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public ArticleListViewModel(@NonNull final ArticlesRepository repository) {
        this.repository = repository;
        load();
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    @NonNull
    public ArticlesAdapter getArticlesAdapter() {
        return articlesAdapter;
    }

    public void load() {
        loading.setValue(true);
        disposable.add(repository.getArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ArticleItem>>() {
                    @Override
                    public void onSuccess(final List<ArticleItem> articleItems) {
                        setAdapter(articleItems);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Log.e(ArticleListViewModel.class.getSimpleName(), "onError: ", e);
                        loading.setValue(false);
                    }
                })
        );
    }

    private void setAdapter(@NonNull final List<ArticleItem> articleItems) {
        articlesAdapter.setArticleItems(articleItems);
        articlesAdapter.notifyDataSetChanged();
    }
}