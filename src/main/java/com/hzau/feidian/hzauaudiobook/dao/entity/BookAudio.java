package com.hzau.feidian.hzauaudiobook.dao.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookAudio {

    private Long id;

    private String name;

    private String bookName;

    private String url;

    private Long bookId;

    private int thumb;

    private boolean thumbed;

    private boolean favourite;

    private int amount;

    public BookAudio(String name, Long bookId) {
        this.name = name;
        this.bookId = bookId;
    }
}
