package com.learn.reactivebookapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.reactivebookapp.model.Book;

@Repository
public interface BookRepository  extends  ReactiveMongoRepository<Book	,Integer>{

}
