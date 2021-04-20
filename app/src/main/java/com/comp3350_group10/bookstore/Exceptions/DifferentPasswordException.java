package com.comp3350_group10.bookstore.Exceptions;

public class DifferentPasswordException extends RuntimeException{
    public DifferentPasswordException(final String msg){
        super(msg);
    }
}
