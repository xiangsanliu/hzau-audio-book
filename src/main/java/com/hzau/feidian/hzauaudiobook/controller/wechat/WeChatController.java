package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.wechat.WeChatService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat/{openid}")
public class WeChatController {

    private final WeChatService weChatService;


    @Autowired
    public WeChatController(WeChatService weChatService) {
        this.weChatService = weChatService;
    }


    @RequestMapping("/getBookById/{id}")
    public ResponseBean getBookById(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok("success", weChatService.getBookById(openid, id));
    }

    @RequestMapping("/getAllShortAudios")
    public ResponseBean getAllShortAudios(@PathVariable String openid) {
        return ResponseBean.ok("success", weChatService.getAllApprovedShortAudios(openid));
    }

    //把openid以前
    @RequestMapping("/getUserInfo")
    public ResponseBean getUserInfo(@PathVariable String openid) {
        return ResponseBean.ok("success", weChatService.getUserInfo(openid));
    }

    @RequestMapping("/comment/shortAudio")
    public ResponseBean commentShortAudio(@PathVariable String openid, @RequestBody String data) {
        weChatService.comment(openid, data, false);
        return ResponseBean.ok("评论成功");
    }

    @RequestMapping("/comment/bookAudio")
    public ResponseBean commentBookAudio(@PathVariable String openid, @RequestBody String data) {
        weChatService.comment(openid, data, true);
        return ResponseBean.ok("评论成功");
    }

    @RequestMapping("/thumb/shortComment/{id}")
    public ResponseBean thumbShortComment(@PathVariable String openid, @PathVariable long id) {
        weChatService.thumbComment(openid, id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("/thumb/bookComment/{id}")
    public ResponseBean thumbBookComment(@PathVariable String openid, @PathVariable long id) {
        weChatService.thumbComment(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/thumb/shortAudio/{id}")
    public ResponseBean thumbShortAudio(@PathVariable String openid, @PathVariable long id) {
        weChatService.thumbAudio(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/thumb/bookAudio/{id}")
    public ResponseBean thumbBookAudio(@PathVariable String openid, @PathVariable long id) {
        weChatService.thumbAudio(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/getCommentsByBook/{id}")
    public ResponseBean getCommentsByBook(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok(null, weChatService.listBookAudioComments(openid, id));
    }

    @RequestMapping("/getCommentsByShort/{id}")
    public ResponseBean getCommentsByShort(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok(null, weChatService.listShortAudioComments(openid, id));
    }

    @RequestMapping("/getFavourite")
    public ResponseBean getFavourite(@PathVariable String openid) {
        return ResponseBean.ok(null, weChatService.getFavourite(openid));
    }

    @RequestMapping("/removeFavouriteBook")
    public ResponseBean removeFavouriteBook(@PathVariable String openid, String data) {
        weChatService.removeFavourite(data, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/removeFavouriteShort")
    public ResponseBean removeFavouriteShort(@PathVariable String openid, String data) {
        weChatService.removeFavourite(data, false);
        return ResponseBean.ok();
    }

}
