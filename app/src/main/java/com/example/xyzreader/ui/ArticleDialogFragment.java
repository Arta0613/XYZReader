package com.example.xyzreader.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.example.xyzreader.R;

import java.util.Objects;

public class ArticleDialogFragment extends DialogFragment {

    private static final String ARTICLE = "article";

    public ArticleDialogFragment() {
    }

    public static ArticleDialogFragment newInstance(final String article) {
        final ArticleDialogFragment articleDialogFragment = new ArticleDialogFragment();

        final Bundle args = new Bundle();
        args.putString(ARTICLE, article);
        articleDialogFragment.setArguments(args);

        return articleDialogFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatTextView) view.findViewById(R.id.article_body_full)).setText(
                Objects.requireNonNull(getArguments()).getString(ARTICLE, getString(R.string.not_found))
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
    }
}