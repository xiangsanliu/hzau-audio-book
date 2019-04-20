package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.Activity;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/12 15:49
 * @comment
 */

@Service
public class ActivityService {

    @Value("${upload-folder}")
    private String baseFolder;

    @Resource
    private ActivityMapper activityMapper;

    public String editActivity(String data) {
        Activity activity = JSON.parseObject(data, Activity.class);
        if (null == activity.getId()) {
            activityMapper.insert(activity);
            return "添加成功";
        }
        activityMapper.update(activity);
        return "编辑成功";
    }

    public List<Activity> listAllActivities() {
        return activityMapper.selectAllActivities();
    }

    public void removeActivity(long id) {
        String name = activityMapper.selectOne(id).getName();
        String path = baseFolder + "activities" + File.separator + name + File.separator + name + ".jpg";
        new File(path).delete();
        activityMapper.delete(id);
    }

    public void uploadPoster(long actId) {
        activityMapper.updatePoster(actId);
    }

}
