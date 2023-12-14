package org.example;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

interface Stock{
    //    ArrayList<Stock> stockList = null;
    public void saveBook(String bTitle,String bAuthor,String bPublisher,String bType,float bPrice);
    public void updateBook();
    public void removeBook(int bid);
    public void searchByCCN(float price,String type,String title);
    //    public void searchByCost();
//    public void searchByCategory();
    public void searchBetweenCostRange(float start,float end);

    public void cheapestBook();
    public void costlyBook();

    public Boolean bookAvailaibility(int bid);

}
