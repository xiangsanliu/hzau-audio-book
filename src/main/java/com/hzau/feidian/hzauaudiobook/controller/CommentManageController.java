package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.CommentManageService;
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
@RequestMapping("/comment")
public class CommentManageController {

    private final CommentManageService commentManageService;

    public CommentManageController(CommentManageService commentManageService) {
        this.commentManageService = commentManageService;
    }

    @RequestMapping("/getAllBookComment")
    public ResponseBean getAllBookComment() {
        return ResponseBean.ok("success", commentManageService.getAllBookComment());
    }

    @RequestMapping("/getAllShortComment")
    public ResponseBean getAllShortComment() {
        return ResponseBean.ok("success", commentManageService.getAllShortComment());
    }

    @RequestMapping("/approveBook/{id}")
    public ResponseBean approveBook(@PathVariable long id) {
        commentManageService.checkBook(id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/approveShort/{id}")
    public ResponseBean approveShort(@PathVariable long id) {
        commentManageService.checkShort(id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("/disapproveBook/{id}")
    public ResponseBean disapproveBook(@PathVariable long id) {
        commentManageService.checkBook(id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("/disapproveShort/{id}")
    public ResponseBean disapproveShort(@PathVariable long id) {
        commentManageService.checkShort(id, false);
        return ResponseBean.ok();
    }


}
