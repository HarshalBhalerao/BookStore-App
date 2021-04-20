package com.comp3350_group10.bookstore.objects;

import junit.framework.TestCase;

public class BookTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetBookName() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals("New Moon",book.getBookName());
    }

    public void testGetBookAuthor() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals("Stephenie Meyer",book.getBookAuthor());
    }

    public void testGetDate() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals("23 February 2010",book.getDate());
    }

    public void testGetPrice() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals(2780,book.getPrice());
    }

    public void testGetBookIsbn() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals("2516511685000",book.getBookIsbn());
    }

    public void testGetStock() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals(4,book.getStock());
    }

    public void testGetReserve() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals(3,book.getReserve());
    }

    public void testGetGenre() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals("Romance",book.getGenre());
    }

    public void testGetImage() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        assertEquals(700056,book.getImage());
    }

    public void testSetStock() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        book.setStock(100);
        assertEquals(100,book.getStock());
    }

    public void testSetPrice() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        book.setPrice(1000);
        assertEquals(1000,book.getPrice());
    }

    public void testSetReserve() {
        IBook book = new Book("New Moon","2516511685000"
                ,4,2780,"23 February 2010"
                ,"Stephenie Meyer","Romance",3,700056);
        assertNotNull(book);
        book.setReserve(5);
        assertEquals(5,book.getReserve());
    }
}