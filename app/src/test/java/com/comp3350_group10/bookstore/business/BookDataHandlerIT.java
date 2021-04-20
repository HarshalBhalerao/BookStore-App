package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookDataHandlerIT {
    private BookDataHandler dataHandler;
    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final IBookDatabase persistence = new BookDatabase(this.tempDB.getAbsolutePath().replace(".script",""));
        this.dataHandler = new BookDataHandler(persistence);
    }

    @Test
    public void checkDbPath(){
        String MainPath = Main.getDBPath();
        String dbPath = this.tempDB.getAbsolutePath().replace(".script","");
        assertEquals("DB is not set properly",dbPath,MainPath);
    }

    @Test
    public void testFindBooks(){
        final List<IBook> bookList;
        final IBook book;
        bookList = dataHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title");
        book = bookList.get(0);
        assertNotNull("The book entered should not be null", bookList);
        assertTrue(book.getBookName().equals(bookList.get(0).getBookName()));
        System.out.println(book.getBookName());
        System.out.println("Finished testing BookDataHandler");
    }

    @Test
    public void testSetBookPrice(){
        //price >0, without exception throw
        final IBook book;
        final List<IBook> bookList = dataHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title");
        book = bookList.get(0);
        dataHandler.setPrice(book, 3000);
        assertNotNull("The book entered should not be null", bookList);
        assertEquals(3000, book.getPrice());
        System.out.println(book.getPrice());
        
        //if price < 0, ADDED BY DANIEL
        final IBook book_1;
        final List<IBook> bookList_1 = dataHandler.findBooks("The Da Vinci Code", true, "Title");
        book_1 = bookList_1.get(0);
        dataHandler.setPrice(book_1, -1);
        assertNotNull("The book entered should not be null", bookList_1);
        assertEquals(2780, book_1.getPrice());
    }

    @Test
    public void testSetBookStock(){
        final IBook book;
        final List<IBook> bookList = dataHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title");
        book  = bookList.get(0);
        dataHandler.setStock(book, 2);
        assertNotNull("The book entered should not be null", bookList);
        assertEquals(2, book.getStock());
        System.out.println(book.getStock());
        //if stock < 0, ADDED BY DANIEL
        final IBook book_1;
        final List<IBook> bookList_1 = dataHandler.findBooks("The Da Vinci Code", true, "Title");
        book_1 = bookList_1.get(0);
        dataHandler.setStock(book_1, -1);
        assertNotNull("The book entered should not be null", bookList_1);
        assertEquals(10, book_1.getStock());
    }
    //increment() / decrement() needs to be tested
    @Test
    public void testIncrementStock(){
        final IBook book;
        final List<IBook> bookList_1 = dataHandler.findBooks("The Da Vinci Code", true, "Title");
        book = bookList_1.get(0);
        dataHandler.incrementStock(book);
        assertNotNull("The book entered should not be null", bookList_1);
        assertEquals("The new stock should be 11",11, book.getStock());
    }

    //without throwing error
    @Test
    public void testDecrementStock(){
        try{
            final IBook book;
            final List<IBook> bookList_1 = dataHandler.findBooks("The Da Vinci Code", true, "Title");
            book = bookList_1.get(0);
            dataHandler.decrementStock(book);
            assertNotNull("The book entered should not be null", bookList_1);
            assertEquals("The new stock should be 9",9, book.getStock());
        }
        catch (NegativeStockException exception){
            assertEquals("It should never reach catch statement",exception.getMessage());
        }

    }
    @Test
    public void testDecrementStockException(){
        try{
            //Eclipse
            final IBook book;
            final List<IBook> bookList_1 = dataHandler.findBooks("Eclipse", true, "Title");
            book = bookList_1.get(0);
            //current stock is 2
            dataHandler.decrementStock(book);
            dataHandler.decrementStock(book);
            dataHandler.decrementStock(book);
        }
        catch (NegativeStockException exception){
            assertEquals("Stock cannot be less than 0.",exception.getMessage());
        }
    }

    @After
    public void tearDown(){
        this.tempDB.delete();//resets DB
    }
}
