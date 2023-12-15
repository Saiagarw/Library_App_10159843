package org.example;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

public class Library implements Stock{

    HashMap<Integer,Book> bookMap;
    public Library() {
        bookMap=new HashMap<>();
    }

    public void saveBook(String bTitle,String bAuthor,String bPublisher,String bType,float bPrice,Date bDate,MySqlConnection jdbcCon){
        Book book=new Book();

        book.bookTitle=bTitle;
        book.bookAuthor=bAuthor;
        book.bookPublisher=bPublisher;
        book.bookType=bType;

        book.bookId= Book.id;

        book.bookPrice=bPrice;
        book.dateAdded= bDate;

        jdbcCon.addBookData(book);
        System.out.println("The book with "+book.bookId+" has been saved");
    }
    @Override
    public void removeBook(MySqlConnection jdbcCon,int bid) {
        jdbcCon.removeBookData(bid);
        System.out.println("Book removed from the library with id "+bid);
    }

    @Override
    public void updateBook(MySqlConnection jdbcCon,int bid) {
        jdbcCon.updateBookData(bid);
    }

    @Override
    public void searchByCCN(MySqlConnection jdbcCon,float price, String type, String title) {
        jdbcCon.searchByCCN(price,type,title);
    }

    @Override
    public void searchBetweenCostRange(MySqlConnection jdbcCon,float start,float end){
        jdbcCon.searchBetweenCostRange(start,end);
    }

    @Override
    public void cheapestBook(MySqlConnection jdbcCon) {
        jdbcCon.cheapestBook();
    }

    @Override
    public void costlyBook(MySqlConnection jdbcCon) {
        jdbcCon.costlyBook();
    }

    @Override
    public void bookAvailaibility(MySqlConnection jdbcCon,int bid) {
        jdbcCon.bookAvailaibility(bid);
    }

    public void removeOldBooks(MySqlConnection jdbcCon){
        jdbcCon.removeOldBooks();
    }
}
