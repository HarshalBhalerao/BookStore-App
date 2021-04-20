package com.comp3350_group10.bookstore.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(final String msg){ super(msg); }
}
