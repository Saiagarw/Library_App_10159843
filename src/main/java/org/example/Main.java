package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    InputStreamReader isr;
    BufferedReader buff;
    int selectedOperation=0;
    public Main() {
        if(isr == null)
            isr = new InputStreamReader(System.in);
        if(buff == null)
            buff = new BufferedReader(isr);
    }

    public int integer_reader(){

        int a=0;
        try {
            a=Integer.parseInt(this.buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public float float_reader(){

        float a=0;
        try {
            a=Float.parseFloat(this.buff.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public String string_reader(){

        String a="";
        try {
            a=this.buff.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static void main(String[] args) {
        Main obj=new Main();
        Library lib=new Library();
        int bid=0;
        MySqlConnection jdbcCon=new MySqlConnection(obj);
        jdbcCon.jdbcConnection();

        while(true) {
            System.out.println("Enter the operation you want to perform.\n 1.SaveBook\n 2.RemoveBook\n 3.UpdateBook\n 4.SearchByCCN\n 5.SearchBetweenCostRange\n 6.Cheapest \n 7.Costliest Book\n 8.CheckBookAvailability\n 9.Remove2yearOldBooks");

            obj.selectedOperation = obj.integer_reader();
            if (obj.selectedOperation == 1) {
                String bTitle, bAuthor, bPublisher, bType;
                float bPrice;

                System.out.println("Enter the Title of the book");
                bTitle = obj.string_reader();

                System.out.println("Enter the Author of the book");
                bAuthor = obj.string_reader();

                System.out.println("Enter the Publisher of the book");
                bPublisher = obj.string_reader();

                System.out.println("Enter the Type of the book");
                bType = obj.string_reader();

                System.out.println("Enter the Price of the book");
                bPrice = obj.integer_reader();

                System.out.println("Enter the date on which the book is added in the library");
                Date bDate;

                String dateString = obj.string_reader();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    bDate = dateFormat.parse(dateString);
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                lib.saveBook(bTitle, bAuthor, bPublisher, bType, bPrice,bDate,jdbcCon);
            }
            else if (obj.selectedOperation == 2) {
                System.out.println("Enter the Id of the book");
                bid = obj.integer_reader();
                lib.removeBook(jdbcCon,bid);
            }
            else if (obj.selectedOperation == 3) {
                System.out.println("Enter the BooKId which you want to update");
                bid= obj.integer_reader();
                lib.updateBook(jdbcCon,bid);
            }
            else if (obj.selectedOperation==4) {
                String bTitle, bType;
                float bPrice;

                System.out.println("Enter the Title of the book");
                bTitle = obj.string_reader();

                System.out.println("Enter the Type of the book");
                bType = obj.string_reader();

                System.out.println("Enter the Price of the book");
                bPrice = obj.integer_reader();

                lib.searchByCCN(jdbcCon,bPrice,bType,bTitle);
            }
            else if (obj.selectedOperation==5) {
                float start,end;
                System.out.println("Enter the start cost range");
                start=obj.integer_reader();
                System.out.println("Enter the end cost range");
                end=obj.integer_reader();
                lib.searchBetweenCostRange(jdbcCon,start,end);
            }
            else if (obj.selectedOperation==6) {
                lib.cheapestBook(jdbcCon);
            }
            else if (obj.selectedOperation==7) {
                lib.costlyBook(jdbcCon);
            }
            else if (obj.selectedOperation==8) {
                System.out.println("Enter the book id you want to check");
                bid=obj.integer_reader();
                lib.bookAvailaibility(jdbcCon,bid);
            }
            else if (obj.selectedOperation==9) {
                lib.removeOldBooks(jdbcCon);
            }
            System.out.println("1.Press 1 for Continue\n 2.Press 2 to discontinue");
            int choice= obj.integer_reader();

            if(choice==2)
                break;
        }

        jdbcCon.jdbcClose();
    }
}