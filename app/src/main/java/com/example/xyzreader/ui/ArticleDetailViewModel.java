package com.example.xyzreader.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.xyzreader.repository.ArticlesRepository;
import com.example.xyzreader.utils.SingleLiveEvent;

public class ArticleDetailViewModel extends ViewModel {

    @NonNull private final ArticlesRepository repository;
    private ArticlesDetailsPagerAdapter articlesDetailsPagerAdapter;
    public final MutableLiveData<String> subtitle = new MutableLiveData<>("Default");
    public final MutableLiveData<String> body = new MutableLiveData<>("Default");
    public final MutableLiveData<String> selectedImage = new MutableLiveData<>("");
    public final SingleLiveEvent<Void> showMore = new SingleLiveEvent<>();
    public final SingleLiveEvent<Void> share = new SingleLiveEvent<>();

    public ArticleDetailViewModel(@NonNull final ArticlesRepository repository) {
        this.repository = repository;
    }

    public void initAdapter(@NonNull final AppCompatActivity activity) {
        articlesDetailsPagerAdapter =
                new ArticlesDetailsPagerAdapter(activity, repository.getArticleItems().size());
    }

    public final ArticlesDetailsPagerAdapter getArticlesDetailsPagerAdapter() {
        return articlesDetailsPagerAdapter;
    }

    public final int getInitialPosition() {
        return repository.getInitialPosition();
    }

    public void updateCurrentItem() {
        subtitle.setValue(repository.getCurrentArticleItem().getSubtitle());
        selectedImage.setValue(repository.getCurrentArticleItem().getCover());
        body.setValue(repository.getCurrentArticleItem().getSampleBody());
    }

    public void showMore() {
        // TODO: investigate how to display excessively large amounts of text in a viewpager
        // It takes too long to measure and display causing bad UX. Moved to text to dialog fragment to offload some pressure
        // Tried RecyclerView by splitting text into paragraphs, each paragraph becoming a recycler item
        // Tried PrecomputedTextCompat to calculate view before setting in background thread but improvements felt minimal
        showMore.call();
    }

    public void share() {
        share.call();
    }
}