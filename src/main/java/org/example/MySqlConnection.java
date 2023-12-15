package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.SimpleDateFormat;

public class MySqlConnection {

	Connection con;
	PreparedStatement pStmt;
	Statement stmt;
	Main obj;
	public MySqlConnection(Main obj1) {
		obj=obj1;
	}

	public void jdbcConnection() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Sailesh1@");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addBookData(Book b) {
		try {
			pStmt = con.prepareStatement("insert into book values(?,?,?,?,?,?,?)");
			pStmt.setInt(1, b.bookId);
			pStmt.setString(2, b.bookTitle);
			pStmt.setString(3, b.bookAuthor);
			pStmt.setString(4, b.bookPublisher);
			pStmt.setString(5, b.bookType);
			pStmt.setFloat(6, b.bookPrice);
			java.sql.Date sqlDate = new java.sql.Date(b.dateAdded.getTime());
			pStmt.setDate(7, sqlDate);
			pStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeBookData(int bid) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("delete from book where BookId = " + bid);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateBookData(int bid){

		try{
			Book b=new Book();
			stmt= con.createStatement();
			ResultSet rs= stmt.executeQuery("select * from book where BookId="+bid);
			while(rs.next()){
				b.bookId=rs.getInt(1);
				b.bookTitle=rs.getString(2);
				b.bookAuthor=rs.getString(3);
				b.bookPublisher=rs.getString(4);
				b.bookType=rs.getString(5);
				b.bookPrice=rs.getFloat(6);
				b.dateAdded=rs.getDate(7);
			}
			System.out.println("Enter the fields you want to update (comma-separated: title,author,type,price,dateAdded):");
			String[] fieldsToUpdate = obj.string_reader().split(",");
			for (String field : fieldsToUpdate) {

				if(field.equals("title"))
				{
					System.out.println("Enter the title you want to update");
					b.bookTitle= obj.string_reader();
				}
				else if(field.equals("author"))
				{
					System.out.println("Enter the author you want to update");
					b.bookAuthor= obj.string_reader();
				}
				else if(field.equals("Publisher"))
				{
					System.out.println("Enter the Publisher you want to update");
					b.bookPublisher= obj.string_reader();
				}
				else if(field.equals("type"))
				{
					System.out.println("Enter the book type you want to update");
					b.bookType=obj.string_reader();
				}
				else if(field.equals("price"))
				{
					System.out.println("Enter the cost you want to update");
					b.bookPrice=obj.float_reader();
				}
				else if(field.equals("dateAdded"))
				{
					System.out.println("Enter the dateAdded you want to update");
					String dateString = obj.string_reader();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					b.dateAdded = dateFormat.parse(dateString);
				}

			}
			String sql = "update book set BookTitle='"+b.bookTitle+"',BookAuthor='"+b.bookAuthor+"',BookPublisher='"+b.bookPublisher+"',BookType='"+b.bookType+"',BookPrice="+b.bookPrice+",BookDateAdded='"+b.dateAdded+"' where BookId="+b.bookId;
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("The book with id "+b.bookId+" is update dsuccessfully");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public void searchByCCN(float price, String type, String title) {

		int x = (int) price;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from book where BookPrice=" + price + " and BookType='" + type + "' and BookTitle='" + title + "'");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4) + " , " + rs.getString(5) + " , " + rs.getInt(6) + " , " + rs.getDate(7));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void searchBetweenCostRange(float start, float end) {
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book where BookPrice between " + start + " and " + end);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4) + " , " + rs.getString(5) + " , " + rs.getInt(6) + " , " + rs.getDate(7));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void cheapestBook() {

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book where BookPrice=(select min(BookPrice) from book)");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4) + " , " + rs.getString(5) + " , " + rs.getInt(6) + " , " + rs.getDate(7));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void costlyBook() {
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book where BookPrice=(select max(BookPrice) from book)");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4) + " , " + rs.getString(5) + " , " + rs.getInt(6) + " , " + rs.getDate(7));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeOldBooks() {

		try {
			stmt = con.createStatement();
			int x=stmt.executeUpdate("delete from book where TIMESTAMPDIFF(year,BookDateAdded,CURDATE())>2");
			System.out.println("No.of books deleted from library which are 2 years old is "+x);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void bookAvailaibility(int bid) {
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) from book where BookId=" + bid);

			while (rs.next()) {
				if (rs.getInt(1) >= 1) {
					System.out.println("Book with id " + bid + " is available");
					return;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Book with id" + bid + " is not available");
	}

	public void jdbcClose() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}