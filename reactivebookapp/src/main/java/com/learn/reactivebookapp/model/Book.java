package com.learn.reactivebookapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="books")
public class Book {
	@Id
	private Integer bookId;
	private String bookTitle;
	private String author;
	private  Integer price;
	
}
