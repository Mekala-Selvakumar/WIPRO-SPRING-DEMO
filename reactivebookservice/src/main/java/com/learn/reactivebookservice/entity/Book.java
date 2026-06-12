package com.learn.reactivebookservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private Integer bookId;
	private String bookTitle;
	private String author;
	private  Integer price;
	
}
