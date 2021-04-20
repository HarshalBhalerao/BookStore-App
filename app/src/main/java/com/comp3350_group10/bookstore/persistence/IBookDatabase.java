/**
 * Interface for BookDatabase
 */

package com.comp3350_group10.bookstore.persistence;

import com.comp3350_group10.bookstore.objects.IBook;

import java.util.List;

public interface IBookDatabase
{
    List<IBook> getBooks();
    IBook updateBook(IBook book);

    //The following methods were not used but was implemented for sustainable development
    IBook insertBook(IBook book);
    void deleteBook(IBook book);
}
