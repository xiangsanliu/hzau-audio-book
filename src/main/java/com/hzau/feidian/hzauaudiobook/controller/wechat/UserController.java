package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.wechat.UserService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/4/15 13:35
 * @comment
 */

@RestController
@RequestMapping("wechat/{openid}/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("getUserInfo")
    public ResponseBean getUserInfo(@PathVariable String openid) {
        return ResponseBean.ok("success", userService.getUserInfo(openid));
    }

}
