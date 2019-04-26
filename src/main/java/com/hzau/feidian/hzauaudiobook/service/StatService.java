package com.hzau.feidian.hzauaudiobook.service;

import com.hzau.feidian.hzauaudiobook.dao.mapper.StatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 项三六
 * @time 2019-04-26 20:08
 * @comment
 */

@Service
public class StatService {

    @Resource
    private StatMapper statMapper;

    public Map<String, Integer> countAll() {
        Map<String, Integer> result = new HashMap<>();
        result.put("bookAmount", statMapper.countBookAmount());
        result.put("shortAmount", statMapper.countShortAmount());
        result.put("bookThumb", statMapper.countBookThumb());
        result.put("shortThumb", statMapper.countShortThumb());
        result.put("bookComment", statMapper.countBookComment());
        result.put("shortComment", statMapper.countShortComment());
        return result;
    }


}
