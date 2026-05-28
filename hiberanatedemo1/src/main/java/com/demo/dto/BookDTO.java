package com.demo.dto;

public class BookDTO {
	private String bookName;
	private int price;
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}
	public BookDTO(String bookName, int price) {
		super();
		this.bookName = bookName;
		this.price = price;
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
		return "BookDTO [bookName=" + bookName + ", price=" + price + "]";
	}

}

