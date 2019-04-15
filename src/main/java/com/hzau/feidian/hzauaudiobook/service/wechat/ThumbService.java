package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.hzau.feidian.hzauaudiobook.dao.mapper.ThumbMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 项三六
 * @time 2019/4/15 12:23
 * @comment
 */

@Service
public class ThumbService {

    @Resource
    private ThumbMapper thumbMapper;

    public void thumbComment(String openid, long id, boolean flag) {
        if (flag) {
            thumbMapper.insertTCBA(openid, id);
        } else {
            thumbMapper.insertTCSA(openid, id);
        }
    }

    public void thumbAudio(String openid, long id, boolean flag) {
        if (flag) {
            thumbMapper.insertTBA(openid, id);
        } else {
            thumbMapper.insertTSA(openid, id);
        }
    }

    public void removeComment(String openid, long id, boolean flag) {
        if (flag) {
            thumbMapper.deleteTCBA(openid, id);
        } else {
            thumbMapper.deleteTCSA(openid, id);
        }
    }

    public void removeAudio(String openid, long id, boolean flag) {
        if (flag) {
            thumbMapper.deleteTBA(openid, id);
        } else {
            thumbMapper.deleteTSA(openid, id);
        }
    }


}
