package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String openid;

    private String name;

    private String majorAndClass;

    private String qq;

    private String phoneNum;

    private String avatarUrl;

    private Integer gender;

    @Deprecated
    private Boolean graduated;

}
