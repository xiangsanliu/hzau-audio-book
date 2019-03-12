package com.hzau.feidian.hzauaudiobook.service;

import com.hzau.feidian.hzauaudiobook.dao.entity.BookList;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WeChatService {

    @Resource
    private BookListMapper bookListMapper;

    public List<BookList> getBookListAndBooks() {
        List<BookList> bookLists = bookListMapper.selectAllBookLists();
        bookLists.forEach(item -> {
            item.setBooks(bookListMapper.selectBooksByBookList(item.getId()));
        });
        return bookLists;
    }

}
