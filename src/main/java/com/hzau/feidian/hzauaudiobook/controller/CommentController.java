package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.CommentService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/4/17 14:58
 * @comment
 */

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/getAllBookComment")
    public ResponseBean getAllBookComment() {
        return ResponseBean.ok("success", commentService.getAllBookComment());
    }

    @RequestMapping("/getAllShortComment")
    public ResponseBean getAllShortComment() {
        return ResponseBean.ok("success", commentService.getAllShortComment());
    }

    @RequestMapping("/approveBook/{id}")
    public ResponseBean approveBook(@PathVariable long id) {
        commentService.checkBook(id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/approveShort/{id}")
    public ResponseBean approveShort(@PathVariable long id) {
        commentService.checkShort(id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/disapproveBook/{id}")
    public ResponseBean disapproveBook(@PathVariable long id) {
        commentService.checkBook(id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("/disapproveShort/{id}")
    public ResponseBean disapproveShort(@PathVariable long id) {
        commentService.checkShort(id, false);
        return ResponseBean.ok();
    }


}
