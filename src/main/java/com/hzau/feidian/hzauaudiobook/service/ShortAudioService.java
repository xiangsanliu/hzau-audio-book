package com.hzau.feidian.hzauaudiobook.service;

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

    public void check(long id, boolean approved) {
        shortAudioMapper.updateApprove(id, approved);
    }

}
