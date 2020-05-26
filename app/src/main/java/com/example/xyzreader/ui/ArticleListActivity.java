package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.xyzreader.R;
import com.example.xyzreader.XYZReaderApplication;
import com.example.xyzreader.databinding.ActivityArticleListBinding;
import com.example.xyzreader.repository.ArticlesRepository;
import com.example.xyzreader.utils.ViewModelFactory;

public class ArticleListActivity extends AppCompatActivity implements ArticleClickListener {

    private ActivityArticleListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);

        final ArticleListViewModel articleListViewModel = new ViewModelProvider(
                this, new ViewModelFactory(getRepository())
        ).get(ArticleListViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(articleListViewModel);

        articleListViewModel.setAdapterClickListener(this);
        articleListViewModel.getArticleEntitiesLiveData().observe(
                this, articleListViewModel::updateArticles
        );
    }

    @Override
    public void onClick(final int position) {
        getRepository().setInitialPosition(position);
        getRepository().setCurrentArticleItem(getRepository().getArticleItems().get(position));

        Intent intent = new Intent(this, ArticleDetailActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, (View) binding.getRoot().findViewById(R.id.thumbnail), "image");
        startActivity(intent, options.toBundle());
    }

    @NonNull
    private ArticlesRepository getRepository() {
        return ((XYZReaderApplication) getApplication()).getAppContainer().getArticlesRepository();
    }
}