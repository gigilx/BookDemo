package com.example.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.api.pojo.Book;
import com.example.api.pojo.ResultBook;
import com.example.api.service.BookService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@CrossOrigin
public class BookController {

    @DubboReference(check = false)
    private BookService bookService;

    @PostMapping("add")
    public ResultBook add(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("delete/{name}")
    public ResultBook delete(@PathVariable("name")String name){
        return bookService.delete(name);
    }

    @GetMapping("query/by-name")
    public ResultBook queryByName(@RequestParam("name")String name){
        return bookService.findByName(name);
    }

    @GetMapping("query/by-author")
    public ResultBook queryByAuthor(@RequestParam("author")String author){
        return bookService.findByAuthor(author);
    }

    @PostMapping("update")
    public ResultBook update(@RequestBody Book book){
        return bookService.update(book);
    }

//    @PostMapping("addByRedis")
//    public ResultBook addBookRedis(@RequestBody Book book) {
//        ResultBook res = bookService.addBookRedis(book);
//        return  res;
//    }
//    @GetMapping("query/by-name-redis")
//    public ResultBook findByNameRedis(@RequestParam("name")String name) {
//        return bookService.findByNameRedis(name);
//    }
}
