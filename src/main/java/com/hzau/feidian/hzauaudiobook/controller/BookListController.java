package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.BookListService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookList/")
public class BookListController {

    private final BookListService bookListService;

    @Autowired
    public BookListController(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    @RequestMapping("editBookList")
    public ResponseBean editBookList(@RequestBody String data) {
        return ResponseBean.ok(bookListService.editBookList(data));
    }

    @RequestMapping("getAllBookLists")
    public ResponseBean getAllBookLists() {
        return ResponseBean.ok("success", bookListService.listAllBookLists());
    }

    @RequestMapping("getBooksByBookList/{bookListId}")
    public ResponseBean getBooksByBookList(@PathVariable("bookListId") long bookListId) {
        return ResponseBean.ok("success", bookListService.listBooksByBookList(bookListId));
    }

    @RequestMapping("saveBook/{bookListId}/{bookId}")
    public ResponseBean saveBook(@PathVariable("bookListId") long bookListId, @PathVariable("bookId") long bookId) {
        bookListService.saveBook(bookListId, bookId);
        return ResponseBean.ok();
    }

    @RequestMapping("remove/{id}")
    public ResponseBean remove(@PathVariable("id") long id) {
        bookListService.removeBookList(id);
        return ResponseBean.ok("删除成功");
    }

    @RequestMapping("removeBook/{bookListId}/{bookId}")
    public ResponseBean removeBook(@PathVariable("bookListId") long bookListId, @PathVariable("bookId") long bookId) {
        bookListService.removeBook(bookListId, bookId);
        return ResponseBean.ok("删除成功");
    }

}
