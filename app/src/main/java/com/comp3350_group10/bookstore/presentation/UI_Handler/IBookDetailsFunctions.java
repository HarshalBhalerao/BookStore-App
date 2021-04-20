package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.widget.ImageView;
import android.widget.TextView;

import com.comp3350_group10.bookstore.business.BookDataHandler;

public interface IBookDetailsFunctions {
    void LoadBookInfo(TextView title, ImageView cover, TextView details);
    void UpdateBookDetails(TextView details);

}
