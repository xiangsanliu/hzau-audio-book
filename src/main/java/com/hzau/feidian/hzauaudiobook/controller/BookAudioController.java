package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.dao.entity.BookAudio;
import com.hzau.feidian.hzauaudiobook.service.BookAudioService;
import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("bookAudio/")
public class BookAudioController {

    private final BookAudioService bookAudioService;

    private final FileService fileService;

    @Autowired
    public BookAudioController(BookAudioService bookAudioService, FileService fileService) {
        this.bookAudioService = bookAudioService;
        this.fileService = fileService;
    }

    @RequestMapping(value = "getBookAudiosByBook/{id}", method = RequestMethod.GET)
    public ResponseBean getBookAudiosByBook(@PathVariable("id") Long id) {
        return ResponseBean.ok("", bookAudioService.listBookAudiosByBook(id));
    }

    @RequestMapping("remove/{id}")
    public ResponseBean remove(@PathVariable("id") long id) {
        bookAudioService.removeOne(id);
        return ResponseBean.ok("删除成功");
    }

    @RequestMapping("upload/{id}/{name}")
    public ResponseBean uploadBookAudio(MultipartFile file, @PathVariable("id") Long bookId,
                                        @PathVariable("name") String bookName) {
        String parentDir = "books" + File.separator + bookName;
        Pair<Boolean, String> result;
        try {
            result = fileService.uploadFile(file, parentDir);
        } catch (IOException e) {
            return ResponseBean.error("IO错误");
        }
        if (result.getKey()) {
            bookAudioService.saveBookAudio(new BookAudio(file.getOriginalFilename(), bookId));
            return ResponseBean.ok("books/" + bookName + '/' + file.getOriginalFilename());
        }
        return ResponseBean.error(result.getValue());
    }

}
