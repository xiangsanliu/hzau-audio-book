package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.BookService;
import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("book/")
public class BookController {

    private final BookService bookService;

    private final FileService fileService;

    @Autowired
    public BookController(BookService bookService, FileService fileService) {
        this.bookService = bookService;
        this.fileService = fileService;
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

    @RequestMapping("upload/{id}/{name}")
    public ResponseBean uploadPic(MultipartFile file, @PathVariable("id") Long bookId,
                                  @PathVariable("name") String bookName) {
        String parentDir = "books" + File.separator + bookName;
        String fileName = bookName + ".jpg";
        Pair<Boolean, String> result;
        try {
            result = fileService.uploadFile(file, parentDir, fileName, true);
        } catch (IOException e) {
            return ResponseBean.error("IO错误");
        }
        if (result.getKey()) {
            bookService.uploadPoster(bookId);
            return ResponseBean.ok("books/" + bookName + '/' + fileName);
        }
        return ResponseBean.error(result.getValue());
    }

}
