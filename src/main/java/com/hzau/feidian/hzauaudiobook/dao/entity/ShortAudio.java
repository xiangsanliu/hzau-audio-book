package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * @author 项三六
 * @time 2019/3/19 14:36
 * @comment
 */

@Getter
@Setter
public class ShortAudio {

    private Long id;

    private String content;

    private Long actId;

    private String openid;

    private String fileName;

    private String actName;

    private MultipartFile file;

    private Boolean approved;

    private Boolean checked;

    private String status;

    private Timestamp uploadTime;

}
