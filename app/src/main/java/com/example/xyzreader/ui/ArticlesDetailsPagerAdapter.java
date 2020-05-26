package com.example.xyzreader.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import java.util.List;

public class ArticlesDetailsPagerAdapter extends FragmentStateAdapter {

    private final int size;

    public ArticlesDetailsPagerAdapter(@NonNull final AppCompatActivity activity, final int size) {
        super(activity);
        this.size = size;
    }

    @NonNull
    @Override
    public Fragment createFragment(final int position) {
        return new ArticleDetailFragment();
    }

    @Override
    public void onBindViewHolder(
            @NonNull final FragmentViewHolder holder,
            final int position,
            @NonNull final List<Object> payloads
    ) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return size;
    }
}