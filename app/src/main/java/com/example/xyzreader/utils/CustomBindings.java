package com.example.xyzreader.utils;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class CustomBindings {

    @BindingAdapter(value = "imageUrl")
    public static void bindImageUrl(final AppCompatImageView imageView, String imageUrl) {

        if (imageUrl.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }
        Glide.with(imageView).load(imageUrl).into(imageView);
    }

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(
            final RecyclerView recyclerView, final RecyclerView.Adapter<?> adapter
    ) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
    }
}