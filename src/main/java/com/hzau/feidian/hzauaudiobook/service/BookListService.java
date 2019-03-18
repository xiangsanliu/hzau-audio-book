package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.dao.entity.BookList;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookListMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookListService {

    @Resource
    private BookListMapper bookListMapper;

    public String editBookList(String data) {
        BookList bookList = JSON.parseObject(data, BookList.class);
        if (null == bookList.getId()) {
            bookListMapper.insert(bookList);
            return "添加成功";
        } else {
            bookListMapper.update(bookList);
            return "编辑成功";
        }
    }

    public void saveBook(long bookListId, long bookId) {
        bookListMapper.insertBook(bookListId, bookId);
    }

    public List<BookList> listAllBookLists() {
        return bookListMapper.selectAllBookLists();
    }

    public List<Book> listBooksByBookList(long bookListId) {
        return bookListMapper.selectBooksByBookList(bookListId);
    }

    @Transactional
    public void removeBookList(long id) {
        bookListMapper.deleteBookList(id);
        bookListMapper.deleteBookListBook(id);
    }

    public void removeBook(long bookListId, long bookId) {
        bookListMapper.deleteBook(bookListId, bookId);
    }

}
