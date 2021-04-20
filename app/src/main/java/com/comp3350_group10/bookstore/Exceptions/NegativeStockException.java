package com.comp3350_group10.bookstore.Exceptions;

public class NegativeStockException extends RuntimeException{
    public NegativeStockException(final String msg){super(msg);}
}

