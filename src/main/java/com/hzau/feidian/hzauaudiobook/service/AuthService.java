package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzau.feidian.hzauaudiobook.dao.entity.User;
import com.hzau.feidian.hzauaudiobook.dao.mapper.UserMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 项三六
 * @time 2019/4/14 12:34
 * @comment
 */

@Service
public class AuthService {

    @Value("${jscode2Session-baseUrl}")
    private String jscode2SessionBaseUrl;

    private OkHttpClient client;

    @Resource
    private UserMapper userMapper;

    public AuthService() {
        client = new OkHttpClient();
    }

    public JSONObject auth(String code, String data) throws IOException {
        User user = JSON.parseObject(data, User.class);
        Request request = new Request.Builder()
                .url(jscode2SessionBaseUrl + code)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject object = JSON.parseObject(Objects.requireNonNull(response.body()).string());
        if (null == object.getInteger("errcode") || 0 == object.getInteger("errcode")) {
            user.setOpenid(object.getString("openid"));
            userMapper.insert(user);
        }
        return object;
    }

}
