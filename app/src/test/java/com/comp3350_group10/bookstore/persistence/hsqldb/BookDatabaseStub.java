package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDatabaseStub implements IBookDatabase {
    private final List<IBook> bookList;

    public BookDatabaseStub(){
        this.bookList = new ArrayList<>();

        bookList.add(new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132));
        bookList.add(new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132));
        bookList.add(new Book("The Da Vinci Code","0218574629654",10,2780,"21 October 2007","Dan Brown","Mystery",3,700104));
        bookList.add(new Book("Angels and Demons","2865926595295",11,2780,"15 June 2009","Dan Brown","Mystery",5,700153));
        bookList.add(new Book("Diary of Wimpy Kid: The Getaway","5987450215825",13,1250,"16 January 2005","Jeff Kinney","Comedy",4, 700031));
        bookList.add(new Book("Diary of Wimpy Kid: Double Down","4578932145250",12,1280,"21 February 2006","Jeff Kinney","Comedy",3,700003));
        bookList.add(new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141));
        bookList.add(new Book("Eclipse","2551819816185",2,2780,"07 April 2008","Stephenie Meyer","Romance",3,700116));
        bookList.add(new Book("New Moon","2516511685000",4,2780,"23 February 2010","Stephenie Meyer","Romance",3,700056));
    }

    @Override
    public List<IBook> getBooks() {
        return Collections.unmodifiableList(bookList);
    }

    @Override
    public IBook insertBook(IBook book) {
        bookList.add(book);
        return book;
    }

    @Override
    public IBook updateBook(IBook book) {
        IBook currentBook = null;
        for(int i=0;i<bookList.size();i++){
            currentBook = bookList.get(i);
            if(currentBook.getBookIsbn().equals(book.getBookIsbn())){
                currentBook.setStock(book.getStock());
                currentBook.setPrice(book.getPrice());
                currentBook.setReserve(book.getReserve());
            }
        }
        return currentBook;
    }

    @Override
    public void deleteBook(IBook book) {
        int index;
        //Finds the index of the book which needs to be deleted from the arrayList
        //Stores it in a variable and deletes it.
        index = bookList.indexOf(book);
        if(index >= 0){
            bookList.remove(index);
        }
    }
}
