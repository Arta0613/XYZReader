package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.xyzreader.R;
import com.example.xyzreader.XYZReaderApplication;
import com.example.xyzreader.databinding.FragmentArticleDetailBinding;
import com.example.xyzreader.repository.ArticlesRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class ArticleDetailFragment extends Fragment {

    private static final int REQUEST_CODE = 10040;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        FragmentArticleDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false);
        final ArticleDetailViewModel viewModel = new ViewModelProvider(requireActivity()).get(ArticleDetailViewModel.class);

        viewModel.showMore.observe(getViewLifecycleOwner(), aVoid -> {
            ArticleDialogFragment articleDialogFragment =
                    ArticleDialogFragment.newInstance(getRepository().getCurrentArticleItem().getBody());
            articleDialogFragment.show(getParentFragmentManager(), "full_text");
        });

        viewModel.share.observe(getViewLifecycleOwner(), aVoid ->
                startActivityForResult(getShareIntent(), REQUEST_CODE)
        );

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content), R.string.sharing, Snackbar.LENGTH_LONG)
                    .setActionTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorAccent))
                    .show();
        }
    }

    @NonNull
    private String getShareText() {
        return getRepository().getCurrentArticleItem().getTitle() + "\n" + getRepository().getCurrentArticleItem().getSubtitle();
    }

    @NonNull
    private ArticlesRepository getRepository() {
        return ((XYZReaderApplication) Objects.requireNonNull(getActivity()).getApplication())
                .getAppContainer().getArticlesRepository();
    }

    @NonNull
    private Intent getShareIntent() {
        return Intent.createChooser(ShareCompat.IntentBuilder.from(Objects.requireNonNull(getActivity()))
                .setType("text/plain")
                .setText(getShareText())
                .getIntent(), getString(R.string.action_share));
    }
}