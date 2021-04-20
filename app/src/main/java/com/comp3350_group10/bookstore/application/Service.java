package com.comp3350_group10.bookstore.application;

import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;

public class Service {
    private static IBookDatabase bookPersistence = null;
    private static IUserDatabase userPersistence = null;

    public static synchronized IBookDatabase setupBookDatabase(){
        if(bookPersistence == null){
            bookPersistence = new BookDatabase(Main.getDBPath());
        }
        return bookPersistence;
    }

    public static synchronized IUserDatabase setupUserDatabase(){
        if(userPersistence == null){
            userPersistence = new UserDatabase(Main.getDBPath());
        }
        return userPersistence;
    }
}
