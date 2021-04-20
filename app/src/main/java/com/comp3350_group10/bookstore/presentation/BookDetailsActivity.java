package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.persistence.UserType;

import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.presentation.UI_Handler.Notify;
import com.comp3350_group10.bookstore.presentation.UI_Handler.BookDetailsFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IBookDetailsFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailsActivity extends AppCompatActivity {
    IBookDetailsFunctions bookDetailsFunctions;
    IButtonFunctions buttonFunctions;
    private TextView bookTitle;
    private ImageView bookImage;
    private TextView details;
    private TextView changePrice;
    private TextView changeStock;

    Notify notify = new Notify();        //Create global notification helper instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notify.checkAndroidVersion(BookDetailsActivity.this);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Link references of Views to variables
        bookTitle = findViewById(R.id.bookDetailsTitle);
        bookImage = findViewById(R.id.bookDetailsImage);
        details = findViewById(R.id.bookDetailsText);
        changePrice = findViewById(R.id.change_price_text);
        changeStock = findViewById(R.id.change_stock_text);

        //Create logic and helper instances
        buttonFunctions = new ButtonFunctions();
        bookDetailsFunctions = new BookDetailsFunctions();
        bookDetailsFunctions.LoadBookInfo(bookTitle, bookImage, details);

        setVisibleLayout();
    }

    //Onclick function for - stock button
    public void OnSaleClick(View v) {
        //normally decrement stock and show confirmation popup if stock > 0
        try {
            buttonFunctions.DecrementStock(details);

            //Notify user when stock is low
            checkLowStock();

            //refresh details to show new updates
            bookDetailsFunctions.UpdateBookDetails(details);
        }

        //if stock is 0 and tried to decrement, show alert
        catch(NegativeStockException e) {
            Messages.viewPopUp(e.getMessage(), this);
        }
    }

    //Onclick function for + stock button
    public void OnReturnClick(View v) {
        buttonFunctions.IncrementStock(details);

        //remove from the "notified" list when stock is back high
        removeNotified();

        //refresh to show updates
        bookDetailsFunctions.UpdateBookDetails(details);
    }

    //Onclick function for set stock button
    public void SetStockOnClick(View v) {
        int value = Integer.parseInt(changeStock.getText().toString());  //take user input from Edittext

        //Set the stock and notify user
        buttonFunctions.SetStock(value);
        Messages.viewPopUp("Stock was changed successfully", this);

        //Update to show updated stock
        bookDetailsFunctions.UpdateBookDetails(details);

        //notify user if stock is low
        checkLowStock();

        //remove book from the notified list if stock is back to >10
        removeNotified();
    }

    //Onclick function for set price button
    public void SetPriceOnClick(View v) {
        float value = Float.parseFloat(changePrice.getText().toString());  //take user input from Edittext

        //convert value into cents to fit database number format
        value *= 100;

        //set the price
        buttonFunctions.SetPrice((int) value);

        //popup notifying user change was made
        Messages.viewPopUp("Price was changed successfully", this);

        //Update to show updated price
        bookDetailsFunctions.UpdateBookDetails(details);
    }

    public void reserveOnClick(View v){
        SwitchActivity.SwitchTo(ContactUsActivity.class, this);
    }
    //Give user access of certain button according to their position privilege
    //Assuming that management layout and changeByOne layout are all set to GONE by default
    private void setVisibleLayout()
    {
        //make sure user is logged in
        if(UserDataHandler.currentUser != null){
            //only management will have privilege to change price and restock
            if(UserDataHandler.currentUser.getUserType() == UserType.Manager)
                findViewById(R.id.management_layout).setVisibility(View.VISIBLE);

            //employees and manager will have privilege to adjust stock according to real time sales
            findViewById(R.id.stock_changeByOne_layout).setVisibility(View.VISIBLE);

            //hide reserve button for non-customer interface
            findViewById(R.id.detail_reserve_button).setVisibility(View.GONE);
        }
    }

    //Notify user when stock is low
    private void checkLowStock() {
        if(BookDataHandler.currentBook.getStock()<10)
            notify.lowStockNotification(this);
    }

    //Remove currentBook from the notified list
    private void removeNotified() {
        if(BookDataHandler.currentBook.getStock()>=10)
            notify.removeFromLowStockList(BookDataHandler.currentBook);
    }
}