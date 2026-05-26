package com.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//by default it  will creat the table -Book   - default tablename  is classname 
//custom tablename
@Entity
@Table(name = "Book_Master")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	@Column(name="book_name", length = 100, nullable = false) //not null column
	private String bookName;
	private int price;

	public Book() {
	}

	public Book(int bookId, String bookName, int price) {
 		this.bookId = bookId;
		this.bookName = bookName;
		this.price = price;
	}

	public Book(String bookName, int price) {
 		this.bookName = bookName;
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Book [bookId=%s, bookName=%s, price=%s]", bookId, bookName, price);
	}

}
