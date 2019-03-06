package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.WeChatService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WeChatController {

    private final WeChatService weChatService;

    @Autowired
    public WeChatController(WeChatService weChatService) {
        this.weChatService = weChatService;
    }

    @RequestMapping("/getBookListAndBooks")
    public ResponseBean getBookListAndBooks() {
        return ResponseBean.ok("success", weChatService.getBookListAndBooks());
    }

}
