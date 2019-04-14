package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.*;
import com.hzau.feidian.hzauaudiobook.dao.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeChatService {

    @Resource
    private BookListMapper bookListMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookAudioMapper bookAudioMapper;

    @Resource
    private ShortAudioMapper shortAudioMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private FavouriteMapper favouriteMapper;

    @Resource
    ThumbMapper thumbMapper;

    public List<BookList> getBookListAndBooks() {
        List<BookList> bookLists = bookListMapper.selectAllBookLists();
        bookLists.forEach(item -> item.setBooks(bookListMapper.selectBooksByBookList(item.getId())));
        return bookLists;
    }

    public Book getBookById(String openid, long id) {
        Book book = bookMapper.selectById(id);
        book.setAudios(bookAudioMapper.selectBookAudiosByBook(openid, id));
        return book;
    }

    public User getUserInfo(String openid) {
        return userMapper.selectByOpenId(openid);
    }

    public void saveShortAudio(ShortAudio audio) {
        shortAudioMapper.insert(audio);
    }

    public List<ShortAudio> getAllApprovedShortAudios(String openid) {
        return shortAudioMapper.selectAllApproved(openid);
    }

    public void comment(String openid, String data, boolean flag) {
        Comment comment = JSON.parseObject(data, Comment.class);
        comment.setOpenid(openid);
        if (flag) {
            commentMapper.insertIntoBookAudio(comment);
        } else {
            commentMapper.insertIntoShortAudio(comment);
        }
    }

    public void thumbComment(String openid, long id, boolean flag) {
        if (flag) {
            thumbMapper.insertTCBA(openid, id);
        } else {
            thumbMapper.insertTCSA(openid, id);
        }
    }

    public void removeFavourite(String data, boolean flag) {
        if (flag) {
            FavouriteBookAudio audio = JSON.parseObject(data, FavouriteBookAudio.class);
            favouriteMapper.deleteFBA(audio.getOpenid(), audio.getBookAudioId());
        } else {
            FavouriteShortAudio audio = JSON.parseObject(data, FavouriteShortAudio.class);
            favouriteMapper.deleteFSA(audio.getOpenid(), audio.getShortAudioId());
        }
    }

    public Map<String, List> getFavourite(String openid) {
        Map<String, List> result = new HashMap<>();
        result.put("book", favouriteMapper.selectFBA(openid));
        result.put("short", favouriteMapper.selectFSA(openid));
        return result;
    }

    public void thumbAudio(String openid, long id, boolean flag) {
        if (flag) {
            thumbMapper.insertTBA(openid, id);
        } else {
            thumbMapper.insertTSA(openid, id);
        }
    }

    public List<Comment> listShortAudioComments(String openid, long id) {
        return commentMapper.selectByShort(openid, id);
    }

    public List<Comment> listBookAudioComments(String openid, long id) {
        return commentMapper.selectByBook(openid, id);
    }

}
