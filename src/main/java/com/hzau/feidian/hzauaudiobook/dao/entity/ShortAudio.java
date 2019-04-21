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

    private String title;

    private Long actId;

    private String openid;

    private String fileName;

    private String actName;

    private MultipartFile file;

    private Boolean approved;

    private Boolean checked;

    // 审核不通过则给出原因
    private String reason;

    private String status;

    private Timestamp uploadTime;

    // 以下为用户信息，用于级联查询，就不新建VO类了

    private String name;

    private String majorAndClass;

    private String qq;

    private String phoneNum;

    private int thumb;

    private boolean thumbed;

    private boolean favourite;

    private int amount;

}
