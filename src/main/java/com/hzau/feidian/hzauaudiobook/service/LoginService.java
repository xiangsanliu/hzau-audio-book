package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSONObject;
import com.hzau.feidian.hzauaudiobook.dao.entity.User;
import com.hzau.feidian.hzauaudiobook.dao.mapper.UserMapper;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 项三六
 * @time 2019/4/17 11:25
 * @comment
 */

@Service
public class LoginService {

    @Resource
    private UserMapper userMapper;

    public ResponseBean login(String data, HttpSession session) {
        System.out.println(session.getId());
        JSONObject jsonObject = JSONObject.parseObject(data);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        User user = userMapper.selectByOpenId(username);

        if (user == null || !username.equals("admin")) {
            return ResponseBean.error("该用户不存在！");
        }
        if (user.getName().equals(password)) {
            session.setAttribute("user", user);
            return ResponseBean.ok("success");
        }
        return ResponseBean.error("密码错误！");
    }

}
