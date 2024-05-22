package com.example.bookdemo.service;

import com.example.bookdemo.pojo.Book;
import com.example.bookdemo.pojo.ResultBook;


public interface BookSerice {
    ResultBook addBook(Book book);
    ResultBook delete(Long id);
    ResultBook  findByName(String name);
    ResultBook  findByAuthor(String author);
    ResultBook  update(Book book);
}
