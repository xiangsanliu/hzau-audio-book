package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.service.WeChatService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/wechat")
public class WeChatController {

    private final WeChatService weChatService;

    private final FileService fileService;

    @Autowired
    public WeChatController(WeChatService weChatService, FileService fileService) {
        this.weChatService = weChatService;
        this.fileService = fileService;
    }

    @RequestMapping("/getBookListAndBooks")
    public ResponseBean getBookListAndBooks() {
        return ResponseBean.ok("success", weChatService.getBookListAndBooks());
    }

    @RequestMapping("/upload/{id}/{name}")
    public ResponseBean uploadFile(MultipartFile file, @PathVariable("id") Long actId,
                                   @PathVariable("name") String actName) throws IOException {
        System.out.println(actId);
        Pair<Boolean, String> result = fileService.uploadFile(file, actName);
        if (result.getKey()) {
            return ResponseBean.ok("上传成功");
        }
        return ResponseBean.error(result.getValue());
    }

}
