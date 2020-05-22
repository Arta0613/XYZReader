package com.example.xyzreader.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StringHelper {

    @NonNull
    public static String getOrReturnEmpty(@Nullable final String s) {
        return s != null ? s : "";
    }
}