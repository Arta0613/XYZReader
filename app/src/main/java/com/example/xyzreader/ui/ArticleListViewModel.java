package com.example.xyzreader.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.xyzreader.database.ArticleEntity;
import com.example.xyzreader.database.ArticleEntityMappers;
import com.example.xyzreader.domain.ArticleItem;
import com.example.xyzreader.repository.ArticlesRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ArticleListViewModel extends ViewModel {

    @NonNull private static final String TAG = ArticleListViewModel.class.getSimpleName();

    @NonNull private final ArticlesRepository repository;
    @NonNull private final CompositeDisposable disposable = new CompositeDisposable();
    @NonNull private final ArticlesAdapter articlesAdapter = new ArticlesAdapter();
    @NonNull private final LiveData<List<ArticleEntity>> articleEntitiesLiveData;
    @NonNull private final ArticleEntityMappers articleEntityMappers = new ArticleEntityMappers();

    public final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public ArticleListViewModel(@NonNull final ArticlesRepository repository) {
        this.repository = repository;
        articleEntitiesLiveData = repository.getSavedArticles();
        load();
    }

    public void setAdapterClickListener(@NonNull final ArticleClickListener listener) {
        articlesAdapter.setClickListener(listener);
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

    @NonNull
    public final LiveData<List<ArticleEntity>> getArticleEntitiesLiveData() {
        return articleEntitiesLiveData;
    }

    public void load() {
        loading.setValue(true);
        disposable.add(repository.getFreshArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ArticleItem>>() {
                    @Override
                    public void onSuccess(final List<ArticleItem> articleItems) {
                        // We are saving to local database once we retrieve new items
                        // Because we are observing a get all entities livedata in the Activity,
                        // When new items are saved, the list is updated.
                        saveArticles(articleItems);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Log.e(TAG, "onError getting from network source: ", e);
                        loading.setValue(false);
                    }
                })
        );
    }

    public void updateArticles(@NonNull final List<ArticleEntity> articleEntities) {
        // TODO: Investigate updating only on new list updates and not rotation/config changes
        final List<ArticleItem> articleItems = new ArrayList<>();
        for (final ArticleEntity articleEntity : articleEntities) {
            articleItems.add(articleEntityMappers.mapArticleItem(articleEntity));
        }

        setAdapter(articleItems);
        loading.setValue(false);
    }

    private void saveArticles(@NonNull final List<ArticleItem> articleItems) {
        final List<ArticleEntity> articleEntities = new ArrayList<>();
        for (final ArticleItem articleItem : articleItems) {
            articleEntities.add(articleEntityMappers.mapArticleEntity(articleItem.getArticle()));
        }

        disposable.add(repository.saveArticles(articleEntities)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Long>>() {
                    @Override
                    public void onSuccess(final List<Long> longs) {
                        Log.d(TAG, "onSuccess: saving new list to local source");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Log.e(TAG, "onError getting from local source: ", e);
                        loading.setValue(false);
                    }
                }));
    }

    private void setAdapter(@NonNull final List<ArticleItem> articleItems) {
        repository.setArticleItems(articleItems);
        articlesAdapter.setArticleItems(articleItems);
        articlesAdapter.notifyDataSetChanged();
    }
}