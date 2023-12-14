package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        while(true) {
            System.out.println("Enter the operation you want to perform.\n 1.SaveBook\n 2.UpdateBook\n 3.RemoveBook\n 4.SearchByCCN\n 5.SearchBetweenCostRange\n 6.Cheapest \n 7.Costliest Book\n 8.CheckBookAvailaibility\n");

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
                lib.saveBook(bTitle, bAuthor, bPublisher, bType, bPrice);
            }
            else if (obj.selectedOperation == 2) {
                System.out.println("Enter the Id of the book");
                bid = obj.integer_reader();
                lib.removeBook(bid);
            }
            else if (obj.selectedOperation == 3) {
                lib.updateBook();
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
                lib.searchByCCN(bPrice,bType,bTitle);
            }
            else if (obj.selectedOperation==5) {
                int start,end;
                System.out.println("Enter the start cost range");
                start=obj.integer_reader();
                System.out.println("Enter the end cost range");
                end=obj.integer_reader();
                lib.searchBetweenCostRange(start,end);
            }
            else if (obj.selectedOperation==6) {

                lib.cheapestBook();
            }
            else if (obj.selectedOperation==7) {
                lib.costlyBook();
            }

            System.out.println("1.Press 1 for Continue\n 2.Press 2 to discontinue");
            int choice= obj.integer_reader();

            if(choice==2)
                break;
        }
    }
}