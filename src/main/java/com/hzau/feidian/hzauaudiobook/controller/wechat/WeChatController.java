package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.service.WeChatService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
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

    @RequestMapping("/getBookById/{id}")
    public ResponseBean getBookById(@PathVariable long id) {
        return ResponseBean.ok("success", weChatService.getBookById(id));
    }

    @RequestMapping("/getAllShortAudios")
    public ResponseBean getAllShortAudios() {
        return ResponseBean.ok("success", weChatService.getAllApprovedShortAudios());
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
            return ResponseBean.ok("success", weChatService.auth(code, data));
        } catch (IOException e) {
            return ResponseBean.error("服务器IO错误", e.toString());
        }
    }

    @RequestMapping("/getUserInfo/{openid}")
    public ResponseBean getUserInfo(@PathVariable String openid) {
        return ResponseBean.ok("success", weChatService.getUserInfo(openid));
    }

    @RequestMapping("/comment/shortAudio")
    public ResponseBean commentShortAudio(@RequestBody String data) {
        weChatService.comment(data, false);
        return ResponseBean.ok("评论成功");
    }

    @RequestMapping("/comment/bookAudio")
    public ResponseBean commentBookAudio(@RequestBody String data) {
        weChatService.comment(data, true);
        return ResponseBean.ok("评论成功");
    }

    @RequestMapping("/thumb/shortComment/{id}")
    public ResponseBean thumbShortComment(@PathVariable long id) {
        weChatService.thumbComment(id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("/thumb/bookComment/{id}")
    public ResponseBean thumbBookComment(@PathVariable long id) {
        weChatService.thumbComment(id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/thumb/shortAudio/{id}")
    public ResponseBean thumbShortAudio(@PathVariable long id) {
        weChatService.thumbAudio(id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("/thumb/bookAudio/{id}")
    public ResponseBean thumbBookAudio(@PathVariable long id) {
        weChatService.thumbAudio(id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/incShortAmount/{id}")
    public ResponseBean incShortAmount(@PathVariable long id) {
        weChatService.incShortAmount(id);
        return ResponseBean.ok();
    }

    @RequestMapping("/incBookAmount/{id}")
    public ResponseBean incBookAmount(@PathVariable long id) {
        weChatService.incBookAmount(id);
        return ResponseBean.ok();
    }

    @RequestMapping("/getCommentsByBook/{id}")
    public ResponseBean getCommentsByBook(@PathVariable long id) {
        return ResponseBean.ok(null, weChatService.listBookAudioComments(id));
    }

    @RequestMapping("/getCommentsByShort/{id}")
    public ResponseBean getCommentsByShort(@PathVariable long id) {
        return ResponseBean.ok(null, weChatService.listShortAudioComments(id));
    }

}
