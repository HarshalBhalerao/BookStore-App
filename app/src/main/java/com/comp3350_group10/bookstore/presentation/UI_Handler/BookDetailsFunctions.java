package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.widget.ImageView;
import android.widget.TextView;

import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.hsqldb.ImageReferences;

import java.text.DecimalFormat;

public class BookDetailsFunctions implements IBookDetailsFunctions{

    @Override
    public void LoadBookInfo(TextView title, ImageView cover, TextView details) {
        title.setText(BookDataHandler.currentBook.getBookName());
        cover.setImageResource(ImageReferences.Get(BookDataHandler.currentBook.getImage()));
        details.setText(FormatBookDetails());
    }

    private String FormatBookDetails() {
        IBook b = BookDataHandler.currentBook;
        String text = b.getBookAuthor() + '\n' + b.getGenre() +'\n' + (b.getBookIsbn() + '\n') + (b.getStock() + " copies remaining\n");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text += ("$" + df.format(b.getPrice()/100f));
        return text;
    }

    public void UpdateBookDetails(TextView details) {
        details.setText(FormatBookDetails());
    }
}