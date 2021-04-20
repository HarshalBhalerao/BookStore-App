package com.comp3350_group10.bookstore.business;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BookDataHandler implements IBookDataHandler {
    private final IBookDatabase bookDatabase;
    public static IBook currentBook;
    private List<IBook> allBooks = null;

    public BookDataHandler(){
        bookDatabase = Service.setupBookDatabase();
    }

    //Dependency injection for testing
    public BookDataHandler(IBookDatabase bookDatabase){
        this.bookDatabase = bookDatabase;
    }

    /**
     * Takes the keyword and search database with it
     *  Returns result after removing duplicated results, and sorted by relevance
     * @param keyword
     * @param asc
     * @param searchBy
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<IBook> findBooks(String keyword, boolean asc, String searchBy) {
        List<String> wordList = splitWords(keyword); //splits keywords

        //bookDatabase.getBooks() is slow, so only do it the first time.
        if (allBooks == null) allBooks = bookDatabase.getBooks();
        List<IBook> bookList = new ArrayList<>(); //stores search result

        //search database with each keyword and combining the lists
        for (IBook book : allBooks) {
            boolean matches = true;
            String title = book.getBookName().toLowerCase();
            String author = book.getBookAuthor().toLowerCase();
            String genre = book.getGenre().toLowerCase();

            for (String word : wordList) {
                word = word.toLowerCase();

                //rejects books that are not exact match
                if (!title.contains(word) && !author.contains(word) && !genre.contains(word)) {
                    matches = false;
                    break;
                }
            }

            if (matches) bookList.add(book);
        }

        //sort the booklist by the appropriate term
        if (searchBy.contains("Title")) sortTitleHelper(bookList);
        else if (searchBy.contains("Author")) sortAuthorHelper(bookList);
        if (searchBy.contains("Genre")) sortGenreHelper(bookList);

        //reverse order if asc=false
        if (!asc) Collections.reverse(bookList);

        return bookList;
    }

    /**
     * Function to set the target book to the given price
     * @param target
     * @param price
     */
    public void setPrice(IBook target, int price) {
        //only change price if price is positive
        if(price>=0) {
            target.setPrice(price);
            bookDatabase.updateBook(target);
        }
    }

    /**
     * Function set the stock for the target book with the given quantity
     * @param target
     * @param quantity
     */
    public void setStock(IBook target, int quantity) {
        //stock cannot be negative
        if(quantity >= 0) {
            target.setStock(quantity);
        }
        bookDatabase.updateBook(target);
    }


    /**
     * Function to increment the stock by 1
     * @param target
     */
    public void incrementStock(IBook target) {
        setStock(target, target.getStock() + 1);
    }


    /**
     * Function to decrement the stock by 1
     * @param target
     * @throws NegativeStockException
     */
    public void decrementStock(IBook target) throws NegativeStockException {
        //only decrease if stock does not go below 0
        if(target.getStock() > 0)
            setStock(target, target.getStock() - 1);

        //throw exception and show popup if it goes below
        else
            throw new NegativeStockException("Stock cannot be less than 0.");
    }

    // splits the given string, ignores non-ascii words
    private List<String> splitWords(String words){
        //split input
        String[] split = words.toLowerCase().split("[-. ,:]+");

        //initialize returning list
        List<String> result = new ArrayList<>();

        //ignore non-ascii and common words
        for(String word:split) {
            if(word.matches("\\A\\p{ASCII}*\\z")){
                result.add(word);
            }
        }

        return result;
    }


    //************************** Sorting Helpers************************************************//
    private void sortTitleHelper(List<IBook> bookList) {
        Collections.sort(bookList, (o1, o2) -> o1.getBookName().compareTo(o2.getBookName()));
    }

    private void sortAuthorHelper(List<IBook> bookList) {
        Collections.sort(bookList, (o1, o2) -> o1.getBookAuthor().compareTo(o2.getBookAuthor()));
    }

    private void sortGenreHelper(List<IBook> bookList) {
        Collections.sort(bookList, (o1, o2) -> o1.getGenre().compareTo(o2.getGenre()));
    }
}
