package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Book {

    private Long id;

    private Long bookListId;

    private String name;

    private String author;

    private String picPath;

    private String desc;

    private List<BookAudio> audios;

    private Boolean posterUploaded;

}
