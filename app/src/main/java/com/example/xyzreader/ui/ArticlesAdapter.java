package com.example.xyzreader.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xyzreader.R;
import com.example.xyzreader.domain.ArticleItem;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    @NonNull private List<ArticleItem> articleItems = new ArrayList<>();
    private ArticleClickListener listener;

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(
            @NonNull final ViewGroup parent, final int viewType
    ) {
        final ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_item_article, parent, false
        );

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return articleItems.size();
    }

    public void setArticleItems(@NonNull final List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    public void setClickListener(@NonNull final ArticleClickListener listener) {
        this.listener = listener;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        public final ViewDataBinding binding;

        public ArticleViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int position) {
            binding.getRoot().setOnClickListener(l -> listener.onClick(position));
            binding.setVariable(BR.articleItem, articleItems.get(position));
            binding.executePendingBindings();
        }
    }
}