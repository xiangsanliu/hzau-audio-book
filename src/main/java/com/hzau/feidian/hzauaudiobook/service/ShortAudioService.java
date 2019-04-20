package com.hzau.feidian.hzauaudiobook.service;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.ShortAudioMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/19 15:42
 * @comment
 */

@Service
public class ShortAudioService {

    @Resource
    private ShortAudioMapper shortAudioMapper;

    public List<ShortAudio> getAll() {
        List<ShortAudio> audios = shortAudioMapper.selectAll();
        audios.forEach(e -> {
            if (!e.getChecked()) {
                e.setStatus("未审核");
            } else if (e.getApproved()) {
                e.setStatus("审核通过");
            } else {
                e.setStatus("审核未通过");
            }
        });
        return audios;
    }

    public void check(long id, boolean approved, String reason) {
        shortAudioMapper.updateApprove(id, approved, reason);
    }

    public void disApprove(String data) {
        ShortAudio shortAudio = JSON.parseObject(data, ShortAudio.class);
        check(shortAudio.getId(), shortAudio.getApproved(), shortAudio.getReason());
    }

}
