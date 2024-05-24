package com.example.consumer.controller;

import com.example.api.pojo.Book;
import com.example.api.pojo.ResultBook;
import com.example.api.service.BookService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@CrossOrigin
public class BookController {

    @DubboReference
    private BookService bookService;

    @PostMapping("add")
    public ResultBook add(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("delete/{id}")
    public ResultBook delete(@PathVariable("id") Long id) {
        return bookService.delete(id);
    }

    @GetMapping("query/by-name")
    public ResultBook queryByName(@RequestParam("name") String name) {
        return bookService.findByName(name);
    }

    @GetMapping("query/by-author")
    public ResultBook queryByAuthor(@RequestParam("author") String author) {
        return bookService.findByAuthor(author);
    }

    @PostMapping("update")
    public ResultBook update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @GetMapping("hello")
    public ResultBook sayHello() {
        return bookService.sayHello();
    }

}
