package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.hzau.feidian.hzauaudiobook.dao.mapper.FavouriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 项三六
 * @time 2019/4/15 12:24
 * @comment
 */

@Service
public class FavouriteService {

    @Resource
    private FavouriteMapper favouriteMapper;


    public void removeFavourite(String openid, long id, boolean flag) {
        if (flag) {
            favouriteMapper.deleteFBA(openid, id);
        } else {
            favouriteMapper.deleteFSA(openid, id);
        }
    }

    public void addFavourite(String openid, long id, boolean flag) {
        if (flag) {
            favouriteMapper.insertFBA(openid, id);
        } else {
            favouriteMapper.insertFSA(openid, id);
        }
    }

    public Map<String, List> getFavourite(String openid) {
        Map<String, List> result = new HashMap<>();
        result.put("book", favouriteMapper.selectFBA(openid));
        result.put("short", favouriteMapper.selectFSA(openid));
        return result;
    }

}
