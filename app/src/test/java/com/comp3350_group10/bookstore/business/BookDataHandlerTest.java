package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabaseStub;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class BookDataHandlerTest extends TestCase {

    private BookDataHandler dataHandler;
    private IBookDatabase bookDatabase;
    private BookDataHandler mockDataHandler;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        IBookDatabase databaseStub = new BookDatabaseStub();
        this.dataHandler = new BookDataHandler(databaseStub);
        bookDatabase = mock(IBookDatabase.class);
        mockDataHandler = new BookDataHandler(bookDatabase);
    }

    @After
    public void tearDown() throws Exception {
        dataHandler = null;
        mockDataHandler = null;
    }

    @Test
    public void testFindBooks() {
        final List<IBook> result;
        List<IBook> list = new ArrayList<>();
        list.add(new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132));
        list.add(new Book("The Da Vinci Code","0218574629654",10,2780,"21 October 2007","Dan Brown","Mystery",3,700104));
        list.add(new Book("Angels and Demons","2865926595295",11,2780,"15 June 2009","Dan Brown","Mystery",5,700153));
        list.add(new Book("Diary of Wimpy Kid: The Getaway","5987450215825",13,1250,"16 January 2005","Jeff Kinney","Comedy",4, 700031));
        list.add(new Book("Diary of Wimpy Kid: Double Down","4578932145250",12,1280,"21 February 2006","Jeff Kinney","Comedy",3,700003));
        list.add(new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141));
        list.add(new Book("Eclipse","2551819816185",2,2780,"07 April 2008","Stephenie Meyer","Romance",3,700116));
        list.add(new Book("New Moon","2516511685000",4,2780,"23 February 2010","Stephenie Meyer","Romance",3,700056));

        when(bookDatabase.getBooks()).thenReturn(list);
        result = mockDataHandler.findBooks(list.get(0).getBookName(),false,"Title");
        assertNotNull("The list should not return NUll",result);
        assertTrue("Harry Potter and the Philosopher Stone".equals(result.get(0).getBookName()));
        verify(bookDatabase).getBooks();
    }

    @Test
    public void testSetPrice() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        dataHandler.setPrice(book_1,1000);
        assertEquals("The new price should be 1000",1000,book_1.getPrice());
        IBook book_2 = new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141);
        dataHandler.setPrice(book_2,2000);
        assertEquals("The new price should be 2000",2000,book_2.getPrice());
        IBook book_3 = new Book("Eclipse","2551819816185",2,2780,"07 April 2008","Stephenie Meyer","Romance",3,700116);
        dataHandler.setPrice(book_3,-5);
        assertEquals("The price shouldn't be negative",2780,book_3.getPrice());
    }
    @Test
    public void testSetStock() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        dataHandler.setStock(book_1,50);
        assertEquals("The new stock should be 50",50,book_1.getStock());
        IBook book_2 = new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141);
        dataHandler.setStock(book_2,-1);
        assertEquals("The new quantity should not be negative",3,book_2.getStock());
    }

    //Have to remind me of how should we carry out these test because they look.. awkward
    @Test
    public void testIncrementStock() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        assertNotNull("The book should not be null",book_1);
        dataHandler.incrementStock(book_1);
        assertEquals("The new stock should be 13",13,book_1.getStock());
    }
    @Test
    public void testDecrementStockException(){
        try{
            IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",0,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
            dataHandler.decrementStock(book_1);
        }
        catch (NegativeStockException exception){
            assertEquals("Stock cannot be less than 0.",exception.getMessage());
        }
    }
    @Test
    public void testDecrementStock() {
        try{
            IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
            assertNotNull("The book should not be null",book_1);
            dataHandler.decrementStock(book_1);
            assertEquals("The new stock should be 11",11,book_1.getStock());
        }
        catch (NegativeStockException exception){
            assertEquals("It should never reach the catch statement",exception.getMessage());
        }

    }
}