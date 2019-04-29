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
        result.put("amount", statMapper.countBookAmount() + statMapper.countShortAmount());
        result.put("thumb", statMapper.countBookThumb() + statMapper.countShortThumb());
        result.put("comment", statMapper.countBookComment() + statMapper.countShortComment());
        return result;
    }

}
