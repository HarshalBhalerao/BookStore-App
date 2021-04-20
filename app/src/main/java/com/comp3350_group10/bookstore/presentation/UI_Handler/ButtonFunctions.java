/**
 * This class acts as a bridge between Logic and GUI
 * All onClick methods will be calling methods from this class
 */

package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.comp3350_group10.bookstore.Exceptions.*;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.IBookDataHandler;
import com.comp3350_group10.bookstore.business.IUserDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.objects.IUser;
import com.comp3350_group10.bookstore.persistence.hsqldb.ImageReferences;
import com.comp3350_group10.bookstore.presentation.BookDetailsActivity;
import com.comp3350_group10.bookstore.presentation.MainActivity;
import com.comp3350_group10.bookstore.presentation.ScreenSize;

import java.text.DecimalFormat;
import java.util.List;

public class ButtonFunctions implements IButtonFunctions
{
    private final IBookDataHandler bookHandler;
    private final IUserDataHandler userHandler;
    private final int IMAGE_HEIGHT = 120;

    public ButtonFunctions()
    {
        bookHandler = new BookDataHandler();
        userHandler = new UserDataHandler();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void SearchButtonPressed(String keyword, TableLayout table, Context context, MainActivity main, boolean asc, String searchBy) {
        //Prevent previous searches from persisting
        ClearResults(table);

        if (keyword.equals("")) main.FillTrendingTable();
        else {
            List<IBook> results = bookHandler.findBooks(keyword, asc, searchBy);
            PopulateResults(results, table, context, main);
        }
    }

    private void ClearResults(TableLayout table) {
        table.removeAllViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<IBook> PopulateResults(List<IBook> results, TableLayout table, Context context, MainActivity main) {
        if (results != null) {
            for (IBook book : results) {
                TableRow row = CreateTableRow(context);

                row.addView(CreateImageView(context, book, ScreenSize.getPixelsFromDP(context, IMAGE_HEIGHT)));
                row.addView(CreateTextView(context, book));
                row.setOnClickListener(v -> OpenBookDetailsActivity(context, book, main));

                table.addView(row);
            }
        }

        return results;
    }

    private void OpenBookDetailsActivity(Context context, IBook book, MainActivity main) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        BookDataHandler.currentBook = book;
        main.startActivity(intent);
    }

    private TableRow CreateTableRow(Context context) {
        TableRow row = new TableRow(context);
        row.setPadding(10,10,10,10);
        return row;
    }

    @SuppressLint("SetTextI18n")
    private TextView CreateTextView(Context context, IBook book) {
        TextView text = new TextView(context);

        float price = book.getPrice() / 100f;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text.setText(book.getBookName() + "\n" + book.getBookAuthor() + "\n" + book.getGenre() + "\n" + book.getBookIsbn() + "\n\n$" + df.format(price));

        return text;
    }

    private ImageView CreateImageView(Context context, IBook book, int height) {
        ImageView image = new ImageView(context);
        image.setImageResource(ImageReferences.Get(book.getImage()));
        image.setLayoutParams(new TableRow.LayoutParams(height, height));
        return image;
    }

    //********************* The following methods calls the corresponding logic method ********************* //
    @Override
    public void LoginButtonPressed(String email, String password) throws UserNotFoundException, DifferentPasswordException {
        userHandler.logIn(email, password);
    }

    @Override
    public void LogoutButtonPressed()
    {
        userHandler.logOut();
    }


    @Override
    public boolean ChangePasswordPressed(String oldPw, String newPw, String confirmNewPw) throws ChangePasswordException
    {
         return userHandler.changePassword(oldPw, newPw, confirmNewPw);
    }

    public void ForgotPasswordPressed(String email) throws UserNotFoundException{
        userHandler.forgotPassword(email);
    }

    @Override
    public void IncrementStock(TextView text)
    {
        bookHandler.incrementStock(BookDataHandler.currentBook);
    }

    @Override
    public void DecrementStock(TextView text) throws NegativeStockException
    {
        bookHandler.decrementStock(BookDataHandler.currentBook);
    }

    @Override
    public void SetStock(int newStock) {
        bookHandler.setStock(BookDataHandler.currentBook, newStock);
    }

    @Override
    public void SetPrice(int newPrice) {
        bookHandler.setPrice(BookDataHandler.currentBook, newPrice);
    }

    @Override
    public IUser CreateUserButtonPressed(String name, String email, String password, boolean isManager) throws CreateUserErrorException, PersistenceException{
        return userHandler.createNewUser(name, email, password, isManager);
    }

    @Override
    public void RemoveUserButtonPressed(String userID) throws PersistenceException, UserNotFoundException{
        userHandler.deleteUser(userID);
    }
}
