package com.comp3350_group10.bookstore.presentation;

import android.content.Context;

public class ScreenSize {
    public static int getPixelsFromDP(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
