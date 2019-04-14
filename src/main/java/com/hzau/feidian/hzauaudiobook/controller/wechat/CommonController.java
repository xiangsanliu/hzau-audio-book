package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.service.AuthService;
import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.service.wechat.WeChatService;
import com.hzau.feidian.hzauaudiobook.service.wechat.CommonService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/4/14 12:25
 * @comment
 */

@RestController
@RequestMapping("/wechat/common")
public class CommonController {

    private final WeChatService weChatService;

    private final FileService fileService;

    private final AuthService authService;

    @Autowired
    private CommonService commonService;

    @Autowired
    public CommonController(WeChatService weChatService, FileService fileService, AuthService authService) {
        this.weChatService = weChatService;
        this.fileService = fileService;
        this.authService = authService;
    }

    @RequestMapping("/getBookListAndBooks")
    public ResponseBean getBookListAndBooks() {
        return ResponseBean.ok("success", weChatService.getBookListAndBooks());
    }

    @RequestMapping("/upload")
    public ResponseBean uploadFile(ShortAudio shortAudio) {
        String parentDir = "activities" + File.separator + shortAudio.getActName();
        String fileName = System.currentTimeMillis() + ".mp3";
        try {
            Pair<Boolean, String> result = fileService.uploadFile(shortAudio.getFile(), parentDir, fileName, false);
            if (result.getKey()) {
                shortAudio.setFileName(fileName);
                weChatService.saveShortAudio(shortAudio);
                return ResponseBean.ok("上传成功");
            }
            return ResponseBean.error(result.getValue());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBean.error("服务器IO错误", e.toString());
        }
    }

    @RequestMapping("/auth/{code}")
    public ResponseBean auth(@PathVariable String code, @RequestBody String data) {
        try {
            return ResponseBean.ok("success", authService.auth(code, data));
        } catch (IOException e) {
            return ResponseBean.error("服务器IO错误", e.toString());
        }
    }

    @RequestMapping("/incShortAmount/{id}")
    public ResponseBean incShortAmount(@PathVariable long id) {
        commonService.incShortAmount(id);
        return ResponseBean.ok();
    }

    @RequestMapping("/incBookAmount/{id}")
    public ResponseBean incBookAmount(@PathVariable long id) {
        commonService.incBookAmount(id);
        return ResponseBean.ok();
    }


    @RequestMapping("/addFavouriteBook")
    public ResponseBean addFavouriteBook(String data) {
        commonService.addFavourite(data, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/addFavouriteShort")
    public ResponseBean addFavouriteShort(String data) {
        commonService.addFavourite(data, false);
        return ResponseBean.ok();
    }

}
