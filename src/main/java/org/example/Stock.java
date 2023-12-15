package org.example;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Date;

interface Stock{
    public void saveBook(String bTitle, String bAuthor, String bPublisher, String bType, float bPrice, Date bDate,MySqlConnection jdbcCon);
    public void updateBook(MySqlConnection jdbcCon,int bid);
    public void removeBook(MySqlConnection jdbcCon,int bid);
    public void searchByCCN(MySqlConnection jdbcCon,float price,String type,String title);
    public void searchBetweenCostRange(MySqlConnection jdbcCon,float start,float end);
    public void cheapestBook(MySqlConnection jdbcCon);
    public void costlyBook(MySqlConnection jdbcCon);
    public void bookAvailaibility(MySqlConnection jdbcCon,int bid);
    public void removeOldBooks(MySqlConnection jdbc);

}
