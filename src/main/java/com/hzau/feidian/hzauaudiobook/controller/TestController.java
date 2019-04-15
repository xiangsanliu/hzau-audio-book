package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final BookService bookService;


    @Autowired
    public TestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("test")
    public List<Book> test() {
        return bookService.listAllBooks();
    }

}
