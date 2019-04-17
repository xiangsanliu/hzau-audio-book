package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.LoginService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 项三六
 * @time 2019/4/17 11:01
 * @comment
 */

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseBean login(@RequestBody String data, HttpSession session) {
        return loginService.login(data, session);
    }

    @RequestMapping(value = "validate", method = RequestMethod.GET)
    public ResponseBean validateLogin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return ResponseBean.ok();
        } else {
            return ResponseBean.error("未登录,请先登录!");
        }
    }

}
