package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.ShortAudioService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/3/18 15:52
 * @comment
 */

@RestController
@RequestMapping("shortAudio/")
public class ShortAudioController {

    private final ShortAudioService shortAudioService;

    @Autowired
    public ShortAudioController(ShortAudioService shortAudioService) {
        this.shortAudioService = shortAudioService;
    }

    @RequestMapping("getAllShortAudios")
    public ResponseBean getAllShortAudios() {
        return ResponseBean.ok("success", shortAudioService.getAll());
    }

    @RequestMapping("approve/{id}")
    public ResponseBean approve(@PathVariable long id) {
        shortAudioService.check(id, true, "");
        return ResponseBean.ok();
    }

    @RequestMapping("disApprove")
    public ResponseBean disApprove(@RequestBody String data) {
        shortAudioService.disApprove(data);
        return ResponseBean.ok();
    }

}
