package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.WeChatService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/4/14 12:25
 * @comment
 */

@RestController
@RequestMapping("/wechat/common")
public class CommonController {

    private final WeChatService weChatService;

    @Autowired
    public CommonController(WeChatService weChatService) {
        this.weChatService = weChatService;
    }

    @RequestMapping("/getBookListAndBooks")
    public ResponseBean getBookListAndBooks(@PathVariable String openid) {
        return ResponseBean.ok("success", weChatService.getBookListAndBooks());
    }

}
