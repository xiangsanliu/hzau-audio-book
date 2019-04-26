package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.StatService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019-04-26 20:37
 * @comment
 */

@RestController
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseBean count() {
        return ResponseBean.ok(null, statService.countAll());
    }

}
