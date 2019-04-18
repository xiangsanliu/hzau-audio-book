package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author 项三六
 * @time 2019/3/21 15:50
 * @comment
 */
@Getter
@Setter
public class Comment {

    private long id;

    private String openid;

    private String content;

    private String name;

    private String avatarUrl;

    // 活动名或书名
    private String parentName;

    private String gender;

    private String majorAndClass;

    private String qq;

    private String phoneNum;

    private String status;

    private Boolean checked;

    private Boolean approved;

    private int thumb;

    private boolean thumbed;

    private Timestamp commentTime;

}
