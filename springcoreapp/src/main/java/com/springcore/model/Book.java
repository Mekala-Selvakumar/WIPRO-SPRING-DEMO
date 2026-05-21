package com.springcore.model;

import org.springframework.stereotype.Component;
//default it will creae a bean with the name book
@Component("book1")
public class Book {
	private int bookId;
	private String bookName;
	private int price;
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
