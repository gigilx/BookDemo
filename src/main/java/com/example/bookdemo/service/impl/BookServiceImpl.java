package com.example.bookdemo.service.impl;

import com.example.bookdemo.common.StatusCode;
import com.example.bookdemo.mapper.BookMapper;
import com.example.bookdemo.pojo.Book;
import com.example.bookdemo.pojo.ResultBook;
import com.example.bookdemo.service.BookSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookSerice {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResultBook addBook(Book book) {
        if(book == null){
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            bookMapper.addBook(book);
            return new ResultBook(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook delete(Long id) {
        if( id <= 0){
            return new ResultBook(StatusCode.PARAM_ERROR);
        }
        try {
            bookMapper.delete(id);
            return new ResultBook(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook findByName(String name) {
        if(name == null || name.isEmpty()) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            List<Book> list = bookMapper.findByName(name);
            if(!list.isEmpty()) {
                ResultBook resBook = new ResultBook(StatusCode.SUCCESS);
                resBook.setBook(list.get(0));
                return resBook;
            }else{
                return new ResultBook(StatusCode.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook findByAuthor(String author) {
        if(author == null || author.isEmpty()) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            List<Book> list = bookMapper.findByAuthor(author);
            ResultBook resBook = new ResultBook(StatusCode.SUCCESS);
            resBook.setBook(list.get(0));
            return resBook;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook update(Book book) {
        if(book == null ) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            bookMapper.update(book);
            return new ResultBook(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }
}
