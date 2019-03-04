package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.BookAudioService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookAudio")
public class BookAudioController {

    private final BookAudioService bookAudioService;

    @Autowired
    public BookAudioController(BookAudioService bookAudioService) {
        this.bookAudioService = bookAudioService;
    }

    @RequestMapping(value = "/getBookAudiosByBook/{id}", method = RequestMethod.GET)
    public ResponseBean getBookAudiosByBook(@PathVariable("id") Long id) {
        return ResponseBean.ok("", bookAudioService.listBookAudiosByBook(id));
    }

    @RequestMapping("/remove/{id}")
    public ResponseBean remove(@PathVariable("id") long id) {
        bookAudioService.removeOne(id);
        return ResponseBean.ok("删除成功");
    }

}
