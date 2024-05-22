package com.example.bookdemo.controller;

import com.example.bookdemo.mapper.BookMapper;
import com.example.bookdemo.pojo.Book;
import com.example.bookdemo.pojo.ResultBook;
import com.example.bookdemo.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @PostMapping("add")
    public ResultBook add(@RequestBody Book book){
        return bookServiceImpl.addBook(book);
    }

    @GetMapping("delete/{id}")
    public ResultBook delete(@PathVariable("id")Long id){
        return bookServiceImpl.delete(id);
    }

    @GetMapping("query/by-name")
    public ResultBook queryByName(@RequestParam("name")String name){
        return bookServiceImpl.findByName(name);
    }

    @GetMapping("query/by-author")
    public ResultBook queryByAuthor(@RequestParam("author")String author){
        return bookServiceImpl.findByAuthor(author);
    }

    @PostMapping("update")
    public ResultBook update(@RequestBody Book book){
        return bookServiceImpl.update(book);
    }

}
