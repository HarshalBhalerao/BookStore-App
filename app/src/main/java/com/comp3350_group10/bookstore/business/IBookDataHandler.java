package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.objects.IBook;

import java.util.List;


public interface IBookDataHandler {

    //function that will find and return a list of books
    // based on what user searched(title/author/ISBN)
    List<IBook> findBooks(String keyword, boolean asc, String sortBy);

    //function to change the price of a particular book
    void setPrice(IBook target, int price);

    //function to increment the stock of a particular book by 1
    void incrementStock(IBook target);

    //function to decrement the stock of a particular book by 1
    void decrementStock(IBook target) throws NegativeStockException;

    //function to change the quantity of stock available for
    // a particular book
    void setStock(IBook target, int quantity);

}
