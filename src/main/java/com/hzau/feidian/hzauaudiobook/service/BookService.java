package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.dao.entity.BookAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookAudioMapper bookAudioMapper;

    public List<Book> listAllBooks() {
        return bookMapper.selectAllBooks();
    }

    public void editBook(String data) {
        Book book = JSON.parseObject(data, Book.class);
        if (null == book.getId()) {
            bookMapper.insertBook(book);
        } else {
            bookMapper.updateBook(book);
        }
    }

    @Transactional
    public void removeOne(long id) {
        bookMapper.deleteOne(id);
        List<BookAudio> bookAudios = bookAudioMapper.selectBookAudiosByBook(id);
        bookAudios.forEach(item -> {
            File file = new File(item.getPath());
            file.delete();
        });
        bookAudioMapper.deleteByBook(id);
    }

}
