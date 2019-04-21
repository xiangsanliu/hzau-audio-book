package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.User;
import com.hzau.feidian.hzauaudiobook.dao.mapper.UserMapper;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
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

    public void editUser(String openid, String data) {
        User user = JSON.parseObject(data, User.class);
        user.setOpenid(openid);
        userMapper.update(user);
    }

}
