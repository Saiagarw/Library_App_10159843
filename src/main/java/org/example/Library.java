package org.example;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Library implements Stock{

    float cheapest= (float) 1e5;
    float costly=0;
    int cheapBookId,costlyBookId;

    HashMap<Integer,Book> bookMap;
    public Library() {
        bookMap=new HashMap<>();
    }

    public void saveBook(String bTitle,String bAuthor,String bPublisher,String bType,float bPrice){
        Book book=new Book();

        book.bookTitle=bTitle;
        book.bookAuthor=bAuthor;
        book.bookPublisher=bPublisher;
        book.bookType=bType;

        book.bookId= Book.id;

        book.bookPrice=bPrice;

//        this.cheapestBook=Math.min(this.cheapestBook,bPrice);
//        this.costlyBook=Math.max(this.costlyBook,bPrice);

        if(this.cheapest>bPrice){
            this.cheapest=bPrice;
            this.cheapBookId=book.bookId;
        }

        if(this.costly<bPrice){
            this.costly=bPrice;
            this.costlyBookId=book.bookId;
        }

        this.bookMap.put(book.bookId,book);
        System.out.println("The book with "+book.bookId+" has been saved");
    }
    @Override
    public void removeBook(int bid) {

        if(!this.bookAvailaibility(bid)){
            System.out.println("Book doesn't exist");
        }

        int flag=0;
        for(HashMap.Entry<Integer,Book> entry:bookMap.entrySet()){
            if(entry.getKey()==bid){
                bookMap.remove(bid);
                flag=1;
                break;
            }
        }

        if(flag==0){
            System.out.println("Book with given id "+ bid +" doesn't exist");
        }
    }

    @Override
    public void updateBook() {

    }

    @Override
    public void searchByCCN(float price, String type, String title) {

        int flag=0;
        for(HashMap.Entry<Integer,Book> entry:bookMap.entrySet()){
            if((entry.getValue().bookPrice==price) && (entry.getValue().bookType==type) && (entry.getValue().bookTitle==title)){
                {
                    System.out.println("Found book with required specification haning id "+entry.getKey());
                    flag=1;
                    break;
                }
            }
        }

        if(flag==0){
            System.out.println("Book doesn't exist with required specification");
        }
    }

    @Override
    public void searchBetweenCostRange(float start,float end){
        int cnt=0;

        for(HashMap.Entry<Integer,Book> entry:bookMap.entrySet()){
            if((entry.getValue().bookPrice>=start) && (entry.getValue().bookPrice<=end)){
                cnt++;
            }
        }

        System.out.println("Total books in the given range are "+cnt);
    }

    @Override
    public void cheapestBook() {
        System.out.println("The cheapest book ID is "+this.cheapBookId);
    }

    @Override
    public void costlyBook() {
        System.out.println("The costly book ID is "+this.costlyBookId);
    }

    @Override
    public Boolean bookAvailaibility(int bid) {

        return bookMap.containsKey(bid);
    }
}
