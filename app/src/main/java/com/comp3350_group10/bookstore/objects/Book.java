/**
 * Book Class for Book Database
 */

package com.comp3350_group10.bookstore.objects;

import java.io.Serializable;

public class Book implements IBook, Serializable {

    //private variables for Book
    private final String isbn;
    private final String bookName;
    private final String author;
    private int price;
    private int stockAmount;
    private final int imageReference;
    private final String date;
    private final String genre;
    private int reserve;

    /**
     * Book constructor: Initializes the book
     * @param author
     * @param isbn
     * @param stockAmount
     * @param price
     * @param date
     * @param author
     * @param genre
     * @param reserve
     */
    public Book(String bookName, String isbn, int stockAmount, int price, String date, String author, String genre, int reserve, int imageReference) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.stockAmount = stockAmount;
        this.date = date;
        this.genre = genre;
        this.reserve = reserve;
        this.imageReference = imageReference;
    }

    //getBookName: Returns the Book Name
    public String getBookName(){
        return bookName;
    }

    //getBookAuthor: Returns the Book Author
    public String getBookAuthor(){
        return author;
    }

    //getDate: Returns the date when the book was published
    public String getDate(){
        return date;
    }

    //getPrice: Returns the Book Price
    public int getPrice(){
        return price;
    }

    //getBookIsbn: Returns the Book ISBN
    public String getBookIsbn(){
        return isbn;
    }

    //getStockAmount: Returns the stock amount
    public int getStock(){
        return stockAmount;
    }

    //getReserve: Returns the amount of users who have reserved this book
    public int getReserve(){
        return reserve;
    }

    //getGenre: Returns the book genre
    public String getGenre(){
        return genre;
    }

    //imageReference: Gets the image reference
    public int getImage(){
        return imageReference;
    }

    //setStockAmount: Updates the stock amount for our books
    public void setStock(int stockAmount){
        this.stockAmount = stockAmount;
    }

    //setPrice: Updates the price
    public void setPrice(int price){
        this.price = price;
    }

    //setReserve: Updates the reserved book amount
    public void setReserve(int reserve){
        this.reserve = reserve;
    }

}
