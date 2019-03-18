package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.service.ActivityService;
import com.hzau.feidian.hzauaudiobook.service.FileService;
import com.hzau.feidian.hzauaudiobook.share.Pair;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/12 15:50
 * @comment
 */

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final FileService fileService;

    private final ActivityService activityService;

    @Autowired
    public ActivityController(FileService fileService, ActivityService activityService) {
        this.fileService = fileService;
        this.activityService = activityService;
    }

    @RequestMapping("/editActivity")
    public ResponseBean editActivity(@RequestBody String data) {
        return ResponseBean.ok(activityService.editActivity(data));
    }

    @RequestMapping("/getAllActivities")
    public ResponseBean getAllActivities() {
        return ResponseBean.ok("success", activityService.listAllActivities());
    }

    @RequestMapping("/remove/{id}")
    public ResponseBean removeActivity(@PathVariable("id") long id) {
        activityService.removeActivity(id);
        return ResponseBean.ok("删除成功");
    }

    @RequestMapping("/upload/{id}/{name}")
    public ResponseBean uploadPic(MultipartFile file, @PathVariable("id") Long actId,
                                  @PathVariable("name") String actName) {
        String parentDir = "activities" + File.separator + actName;
        String fileName = actName + ".jpg";
        Pair<Boolean, String> result;
        try {
            result = fileService.uploadFile(file, parentDir, fileName, true);
        } catch (IOException e) {
            return ResponseBean.error("IO错误");
        }
        if (result.getKey()) {
            return ResponseBean.ok("activities/" + actName + '/' + fileName);
        }
        return ResponseBean.error(result.getValue());
    }

}
