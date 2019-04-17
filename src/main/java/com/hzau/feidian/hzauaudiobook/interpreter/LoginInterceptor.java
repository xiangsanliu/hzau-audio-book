package com.hzau.feidian.hzauaudiobook.interpreter;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.User;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/4/17 11:09
 * @comment
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登录
            sendError(response, ResponseBean.auth());
            return false;
        } else {
            return true;
        }
    }

    private void sendError(HttpServletResponse response, ResponseBean responseBean) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(responseBean));
    }
}
