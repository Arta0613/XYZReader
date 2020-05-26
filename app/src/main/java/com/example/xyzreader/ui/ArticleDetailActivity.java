package com.example.xyzreader.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.xyzreader.R;
import com.example.xyzreader.XYZReaderApplication;
import com.example.xyzreader.databinding.ActivityArticleDetailBinding;
import com.example.xyzreader.repository.ArticlesRepository;
import com.example.xyzreader.utils.ViewModelFactory;

import java.util.Objects;

public class ArticleDetailActivity extends AppCompatActivity {

    private ActivityArticleDetailBinding binding;
    private ArticleDetailViewModel articleDetailViewModel;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.collapsingToolbarLayout.setTitle(getRepository().getCurrentArticleItem().getTitle());

        articleDetailViewModel = new ViewModelProvider(
                this, new ViewModelFactory(getRepository())
        ).get(ArticleDetailViewModel.class);

        binding.setLifecycleOwner(this);
        articleDetailViewModel.initAdapter(this);
        binding.setViewModel(articleDetailViewModel);

        binding.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(final int position) {
                getRepository().setCurrentArticleItem(getRepository().getArticleItems().get(position));
                binding.collapsingToolbarLayout.setTitle(getRepository().getCurrentArticleItem().getTitle());
                articleDetailViewModel.updateCurrentItem();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private ArticlesRepository getRepository() {
        return ((XYZReaderApplication) getApplication()).getAppContainer().getArticlesRepository();
    }
}