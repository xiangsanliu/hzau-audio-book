package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 项三六
 * @time 2019/4/21 17:04
 * @comment
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseBean handleException(RuntimeException e) {
        LoggerFactory.getLogger(e.getClass()).error(e.getMessage());
        return ResponseBean.error("Database Error!", e.getMessage());
    }

}
