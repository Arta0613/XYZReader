package com.example.xyzreader.utils;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.example.xyzreader.R;
import com.example.xyzreader.ui.ArticlesDetailsPagerAdapter;

import static com.bumptech.glide.GenericTransitionOptions.with;

public class CustomBindings {

    @BindingAdapter(value = "imageUrl")
    public static void bindImageUrl(
            @NonNull final AppCompatImageView imageView,
            @NonNull String imageUrl
    ) {
        if (imageUrl.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        } else imageView.setVisibility(View.VISIBLE);

        Glide.with(imageView)
                .load(imageUrl)
                .transition(with(DrawableAlwaysCrossFadeFactory.getInstance()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(
            @NonNull final RecyclerView recyclerView, final RecyclerView.Adapter<?> adapter
    ) {
        recyclerView.setLayoutManager(new GridLayoutManager(
                recyclerView.getContext(),
                recyclerView.getContext().getResources().getInteger(R.integer.span_count))
        );
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"setPagerAdapter", "currentPosition"})
    public static void bindViewPagerAdapter(
            @NonNull final ViewPager2 viewPager,
            final ArticlesDetailsPagerAdapter adapter,
            final int initialPosition
    ) {
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initialPosition, false);
    }

    private static class DrawableAlwaysCrossFadeFactory implements TransitionFactory<Drawable> {

        private final DrawableCrossFadeTransition resourceTransition =
                new DrawableCrossFadeTransition(500, true);

        private static DrawableAlwaysCrossFadeFactory fadeFactory;

        public static DrawableAlwaysCrossFadeFactory getInstance() {
            if (fadeFactory == null) {
                fadeFactory = new DrawableAlwaysCrossFadeFactory();
            }

            return fadeFactory;
        }

        @Override
        public Transition<Drawable> build(
                final DataSource dataSource, final boolean isFirstResource
        ) {
            return resourceTransition;
        }
    }
}