package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.service.wechat.AudioService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("wechat/{openid}/audio/")
public class AudioController {

    private final AudioService audioService;

    private final FileService fileService;

    @Autowired
    public AudioController(AudioService audioService, FileService fileService) {
        this.audioService = audioService;
        this.fileService = fileService;
    }

    @RequestMapping("getBookById/{id}")
    public ResponseBean getBookById(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok("success", audioService.getBookById(openid, id));
    }

    @RequestMapping("getActById/{id}")
    public ResponseBean getActById(@PathVariable String openid, @PathVariable long id) {
        return ResponseBean.ok(null, audioService.getActById(openid, id));
    }

    @RequestMapping("getAllShortAudios")
    public ResponseBean getAllShortAudios(@PathVariable String openid) {
        return ResponseBean.ok("success", audioService.getAllApprovedShortAudios(openid));
    }

    @RequestMapping("getMyShortAudios")
    public ResponseBean getMyShortAudios(@PathVariable String openid) {
        return ResponseBean.ok("success", audioService.getMyShortAudios(openid));
    }

    @RequestMapping("upload")
    public ResponseBean uploadFile(ShortAudio shortAudio) {
        String parentDir = "activities" + File.separator + shortAudio.getActName();
        String fileName = System.currentTimeMillis() + ".mp3";
        try {
            Pair<Boolean, String> result = fileService.uploadFile(shortAudio.getFile(), parentDir, fileName, false);
            if (result.getKey()) {
                shortAudio.setFileName(fileName);
                audioService.saveShortAudio(shortAudio);
                return ResponseBean.ok("上传成功");
            }
            return ResponseBean.error(result.getValue());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBean.error("服务器IO错误", e.toString());
        }
    }

}
