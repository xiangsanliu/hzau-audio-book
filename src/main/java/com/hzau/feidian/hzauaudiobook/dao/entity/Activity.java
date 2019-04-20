package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/17 11:44
 * @comment
 */

@Getter
@Setter
@ToString
public class Activity {

    private Long id;

    private String name;

    private String desc;

    private Boolean posterUploaded;

    private List<ShortAudio> shortAudios;

}
