package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
