package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.wechat.CommentService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/4/15 12:15
 * @comment
 */

@RestController
@RequestMapping("/wechat/{openid}/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/getByBook/{id}")
    public ResponseBean getByBook(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok(null, commentService.listBookAudioComments(openid, id));
    }

    @RequestMapping("/getByShort/{id}")
    public ResponseBean getByShort(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok(null, commentService.listShortAudioComments(openid, id));
    }

    @RequestMapping("/shortAudio")
    public ResponseBean shortAudio(@PathVariable String openid, @RequestBody String data) {
        commentService.comment(openid, data, false);
        return ResponseBean.ok("评论成功");
    }

    @RequestMapping("/bookAudio")
    public ResponseBean bookAudio(@PathVariable String openid, @RequestBody String data) {
        commentService.comment(openid, data, true);
        return ResponseBean.ok("评论成功");
    }

}
