package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.User;
import com.hzau.feidian.hzauaudiobook.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 项三六
 * @time 2019/4/15 13:36
 * @comment
 */

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserInfo(String openid) {
        return userMapper.selectByOpenId(openid);
    }


}
