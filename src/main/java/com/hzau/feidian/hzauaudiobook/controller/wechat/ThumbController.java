package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.wechat.ThumbService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/4/15 12:12
 * @comment
 */

@RestController
@RequestMapping("wechat/{openid}/thumb")
public class ThumbController {

    private final ThumbService thumbService;

    public ThumbController(ThumbService thumbService) {
        this.thumbService = thumbService;
    }

    @RequestMapping("thumbShortComment/{id}")
    public ResponseBean thumbShortComment(@PathVariable String openid, @PathVariable long id) {
        thumbService.thumbComment(openid, id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("thumbBookComment/{id}")
    public ResponseBean thumbBookComment(@PathVariable String openid, @PathVariable long id) {
        thumbService.thumbComment(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("thumbShortAudio/{id}")
    public ResponseBean thumbShortAudio(@PathVariable String openid, @PathVariable long id) {
        thumbService.thumbAudio(openid, id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("thumbBookAudio/{id}")
    public ResponseBean thumbBookAudio(@PathVariable String openid, @PathVariable long id) {
        thumbService.thumbAudio(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("removeShortComment/{id}")
    public ResponseBean removeShortComment(@PathVariable String openid, @PathVariable long id) {
        thumbService.removeComment(openid, id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("removeBookComment/{id}")
    public ResponseBean removeBookComment(@PathVariable String openid, @PathVariable long id) {
        thumbService.removeComment(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("removeShortAudio/{id}")
    public ResponseBean removeShortAudio(@PathVariable String openid, @PathVariable long id) {
        thumbService.removeAudio(openid, id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("removeBookAudio/{id}")
    public ResponseBean removeBookAudio(@PathVariable String openid, @PathVariable long id) {
        thumbService.removeAudio(openid, id, true);
        return ResponseBean.ok();
    }

}
