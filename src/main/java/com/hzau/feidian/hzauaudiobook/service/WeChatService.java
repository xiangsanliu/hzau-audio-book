package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.dao.entity.BookList;
import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookListMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ShortAudioMapper;
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


    public JSONObject auth(String code) throws IOException {
        Request request = new Request.Builder()
                .url(jscode2SessionBaseUrl + code)
                .build();
        Response response = client.newCall(request).execute();
        return JSON.parseObject(Objects.requireNonNull(response.body()).string());
    }

    public void saveShortAudio(ShortAudio audio) {
        shortAudioMapper.insert(audio);
    }

    public List<ShortAudio> getAllApprovedShortAudios() {
        return shortAudioMapper.selectAllApproved();
    }

}
