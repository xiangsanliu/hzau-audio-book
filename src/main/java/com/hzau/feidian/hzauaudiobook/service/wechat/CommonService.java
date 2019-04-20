package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.Activity;
import com.hzau.feidian.hzauaudiobook.dao.entity.BookList;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ActivityMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookListMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ShortAudioMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/4/14 12:42
 * @comment
 */

@Service
public class CommonService {

    @Resource
    private BookAudioMapper bookAudioMapper;

    @Resource
    private ShortAudioMapper shortAudioMapper;

    @Resource
    private BookListMapper bookListMapper;

    @Resource
    private ActivityMapper activityMapper;

    public List<BookList> getBookListAndBooks() {
        List<BookList> bookLists = bookListMapper.selectAllBookLists();
        bookLists.forEach(item -> item.setBooks(bookListMapper.selectBooksByBookList(item.getId())));
        return bookLists;
    }

    public List<Activity> getAllActivities() {
        return activityMapper.selectAllActivities();
    }

    public void incBookAmount(long id) {
        bookAudioMapper.incAmount(id);
    }

    public void incShortAmount(long id) {
        shortAudioMapper.incAmount(id);
    }

}
