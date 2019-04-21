package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/27 21:51
 * @comment
 */

@Getter
@Setter
public class FavouriteBookAudio {

    private String openid;

    private Long bookAudioId;

    private String name;

    private String bookName;

    private Long bookId;

    private Boolean favourite;

    private int thumb;

    private Boolean thumbed;

}
