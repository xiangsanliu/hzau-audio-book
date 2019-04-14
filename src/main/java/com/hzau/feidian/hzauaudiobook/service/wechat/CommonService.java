package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.FavouriteBookAudio;
import com.hzau.feidian.hzauaudiobook.dao.entity.FavouriteShortAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.FavouriteMapper;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ShortAudioMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 项三六
 * @time 2019/4/14 12:42
 * @comment
 */

@Service
public class CommonService {

    @Resource
    private FavouriteMapper favouriteMapper;

    @Resource
    private BookAudioMapper bookAudioMapper;

    @Resource
    private ShortAudioMapper shortAudioMapper;


    public void incBookAmount(long id) {
        bookAudioMapper.incAmount(id);
    }

    public void incShortAmount(long id) {
        shortAudioMapper.incAmount(id);
    }

    public void addFavourite(String data, boolean flag) {
        if (flag) {
            FavouriteBookAudio audio = JSON.parseObject(data, FavouriteBookAudio.class);
            favouriteMapper.insertFBA(audio.getOpenid(), audio.getBookAudioId());
        } else {
            FavouriteShortAudio audio = JSON.parseObject(data, FavouriteShortAudio.class);
            favouriteMapper.insertFSA(audio.getOpenid(), audio.getShortAudioId());
        }
    }


}
