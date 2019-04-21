package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/27 21:54
 * @comment
 */
@Getter
@Setter
public class FavouriteShortAudio {

    private String openid;

    private String title;

    private Long shortAudioId;

    private String actName;

    private String content;

    private String fileName;

    private String name;

    private String majorAndClass;

    private String avatarUrl;

    private Boolean favourite;

    private int thumb;

    private Boolean thumbed;

}
