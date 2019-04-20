package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ShortAudioMapper;
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


    public Book getBookById(String openid, long id) {
        Book book = bookMapper.selectById(id);
        book.setAudios(bookAudioMapper.selectBookAudiosByBook(openid, id));
        return book;
    }

    public void saveShortAudio(ShortAudio audio) {
        shortAudioMapper.insert(audio);
    }

    public List<ShortAudio> getAllApprovedShortAudios(String openid) {
        return shortAudioMapper.selectAllApproved(openid);
    }

    public List<ShortAudio> getMyShortAudios(String openid) {
        List<ShortAudio> shortAudios = shortAudioMapper.selectMy(openid);
        shortAudios.forEach(e -> {
            if (!e.getChecked()) {
                e.setStatus("未审核");
            } else if (e.getApproved()) {
                e.setStatus("审核通过");
            } else {
                e.setStatus("审核未通过");
            }
        });
        return shortAudioMapper.selectMy(openid);
    }


}
