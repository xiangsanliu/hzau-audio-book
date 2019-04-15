package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.AuthService;
import com.hzau.feidian.hzauaudiobook.service.wechat.CommonService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/4/14 12:25
 * @comment 公共数据
 */

@RestController
@RequestMapping("wechat/common/")
public class CommonController {

    private final AuthService authService;

    private final CommonService commonService;

    @Autowired
    public CommonController(AuthService authService, CommonService commonService) {
        this.authService = authService;
        this.commonService = commonService;
    }

    @RequestMapping("getBookListAndBooks")
    public ResponseBean getBookListAndBooks() {
        return ResponseBean.ok("success", commonService.getBookListAndBooks());
    }

    @RequestMapping("auth/{code}")
    public ResponseBean auth(@PathVariable String code, @RequestBody String data) {
        try {
            return ResponseBean.ok("success", authService.auth(code, data));
        } catch (IOException e) {
            return ResponseBean.error("服务器IO错误", e.toString());
        }
    }

    @RequestMapping("incShortAmount/{id}")
    public ResponseBean incShortAmount(@PathVariable long id) {
        commonService.incShortAmount(id);
        return ResponseBean.ok();
    }

    @RequestMapping("incBookAmount/{id}")
    public ResponseBean incBookAmount(@PathVariable long id) {
        commonService.incBookAmount(id);
        return ResponseBean.ok();
    }

}
