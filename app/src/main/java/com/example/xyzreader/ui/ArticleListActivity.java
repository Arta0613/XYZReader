package com.example.xyzreader.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.xyzreader.R;
import com.example.xyzreader.XYZReaderApplication;
import com.example.xyzreader.databinding.ActivityArticleListBinding;
import com.example.xyzreader.repository.ArticlesRepository;
import com.example.xyzreader.utils.ViewModelFactory;

public class ArticleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityArticleListBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_article_list);

        final ArticleListViewModel articleListViewModel = new ViewModelProvider(
                this, new ViewModelFactory(getRepository())
        ).get(ArticleListViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(articleListViewModel);
    }

    @NonNull
    private ArticlesRepository getRepository() {
        return ((XYZReaderApplication) getApplication()).getAppContainer().getArticlesRepository();
    }
}