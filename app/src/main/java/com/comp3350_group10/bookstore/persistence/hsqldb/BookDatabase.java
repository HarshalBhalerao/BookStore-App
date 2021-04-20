/**
 * HSQLDB Book Database
 */

package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.Exceptions.PersistenceException;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;


public class BookDatabase implements IBookDatabase {

    private final String dbPath;
    private List<IBook> bookList;

    public BookDatabase(String dbPath) {
        this.dbPath = dbPath;
        this.bookList = getBooks();
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Book createBook(ResultSet rs) throws SQLException{
         String bookName = rs.getString("bookName");
         String isbn = rs.getString("isbn");
         int quantity = rs.getInt("quantity");
         int price = rs.getInt("price");
         String date = rs.getString("date");
         String author = rs.getString("author");
         String genre = rs.getString("genre");
         int reserve = rs.getInt("reserve");
         int imageReference = rs.getInt("image");
        return new Book(bookName, isbn, quantity, price, date, author, genre, reserve, imageReference);
    }

    @Override
    public List<IBook> getBooks() throws PersistenceException{
        List<IBook> books = new ArrayList<>();
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();
            ResultSet rtst = stmt.executeQuery("SELECT * FROM books");

            while(rtst.next()){
                Book book = createBook(rtst);
                books.add(book);
            }
            rtst.close();
            stmt.close();
            return books;

        } catch (final SQLException e) {
            throw new PersistenceException("can't get the books");
        }
    }

    @Override
    public IBook insertBook(IBook book) {
        try(Connection c = connection()) {
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getBookIsbn());
            pstmt.setInt(3, book.getStock());
            pstmt.setInt(4, book.getPrice());
            pstmt.setString(5, book.getDate());
            pstmt.setString(6, book.getBookAuthor());
            pstmt.setString(7, book.getGenre());
            pstmt.setInt(8, book.getReserve());
            pstmt.setInt(9, book.getImage());
            pstmt.executeUpdate();

            //have to update the bookList
            this.bookList =getBooks();
            return book;
        } catch (SQLException e) {
            throw new PersistenceException("can't insert the book.");
        }
    }

    @Override
    public IBook updateBook(IBook book) {

        try (Connection c = connection()){
            PreparedStatement pstmt = c.prepareStatement("UPDATE books SET quantity=?,price=?,reserve=? WHERE isbn = ?");
            pstmt.setInt(1, book.getStock());
            pstmt.setInt(2, book.getPrice());
            pstmt.setInt(3, book.getReserve());
            pstmt.setString(4, book.getBookIsbn());
            pstmt.executeUpdate();
            //update the bookList
            this.bookList=getBooks();
            return book;
        }
        catch(SQLException e){
            throw new PersistenceException("can't update the book");
        }
    }

    @Override
    public void deleteBook(IBook book) {
        try( Connection c = connection()){
            PreparedStatement pstmt = c.prepareStatement("DELETE FROM books WHERE isbn=?");
            pstmt.setString(1, book.getBookIsbn());
            //update the bookList
            this.bookList = getBooks();
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            throw new PersistenceException("can't delete the book");
        }
    }
}