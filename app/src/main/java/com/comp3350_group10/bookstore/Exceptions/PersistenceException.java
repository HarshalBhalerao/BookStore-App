package com.comp3350_group10.bookstore.Exceptions;

public class PersistenceException extends RuntimeException {
    public PersistenceException(final String msg) {
        super(msg);
    }
}

