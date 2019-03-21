package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzau.feidian.hzauaudiobook.dao.entity.*;
import com.hzau.feidian.hzauaudiobook.dao.mapper.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class WeChatService {

    @Value("${jscode2Session-baseUrl}")
    private String jscode2SessionBaseUrl;

    private OkHttpClient client;

    public WeChatService() {
        client = new OkHttpClient();
    }

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

    public List<BookList> getBookListAndBooks() {
        List<BookList> bookLists = bookListMapper.selectAllBookLists();
        bookLists.forEach(item -> item.setBooks(bookListMapper.selectBooksByBookList(item.getId())));
        return bookLists;
    }

    public Book getBookById(long id) {
        Book book = bookMapper.selectById(id);
        book.setAudios(bookAudioMapper.selectBookAudiosByBook(id));
        return book;
    }

    public JSONObject auth(String code, String data) throws IOException {
        User user = JSON.parseObject(data, User.class);
        Request request = new Request.Builder()
                .url(jscode2SessionBaseUrl + code)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject object = JSON.parseObject(Objects.requireNonNull(response.body()).string());
        if (null == object.getInteger("errcode") || 0 == object.getInteger("errcode")) {
            user.setOpenid(object.getString("openid"));
            userMapper.insert(user);
        }
        return object;
    }

    public User getUserInfo(String openid) {
        return userMapper.selectByOpenId(openid);
    }

    public void saveShortAudio(ShortAudio audio) {
        shortAudioMapper.insert(audio);
    }

    public List<ShortAudio> getAllApprovedShortAudios() {
        return shortAudioMapper.selectAllApproved();
    }

    public void comment(String data, boolean flag) {
        Comment comment = JSON.parseObject(data, Comment.class);
        if (flag) {
            commentMapper.insertIntoBookAudio(comment);
        } else {
            commentMapper.insertIntoShortAudio(comment);
        }
    }

    public void thumbComment(long id, boolean flag) {
        if (flag) {
            commentMapper.incBookThumb(id);
        } else {
            commentMapper.incShortThumb(id);
        }
    }

    public void thumbAudio(long id, boolean flag) {
        if (flag) {
            bookAudioMapper.incThumb(id);
        } else {
            shortAudioMapper.incThumb(id);
        }
    }

    public void incBookAmount(long id) {
        bookAudioMapper.incAmount(id);
    }

    public void incShortAmount(long id) {
        shortAudioMapper.incAmount(id);
    }

}
