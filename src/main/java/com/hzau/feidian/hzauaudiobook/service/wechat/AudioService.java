package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.dao.entity.User;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ShortAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AudioService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookAudioMapper bookAudioMapper;

    @Resource
    private ShortAudioMapper shortAudioMapper;

    @Resource
    private UserMapper userMapper;

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


}
