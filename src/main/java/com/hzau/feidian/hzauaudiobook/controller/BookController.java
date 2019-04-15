package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.BookService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("getAllBooks")
    public ResponseBean getAllBooks() {
        return ResponseBean.ok("success", bookService.listAllBooks());
    }

    @RequestMapping("editBook")
    public ResponseBean editBook(@RequestBody String data) {
        bookService.editBook(data);
        return ResponseBean.ok("成功");
    }

    @RequestMapping("remove/{id}")
    public ResponseBean remove(@PathVariable("id") long id) {
        bookService.removeOne(id);
        return ResponseBean.ok("删除成功");
    }

}
