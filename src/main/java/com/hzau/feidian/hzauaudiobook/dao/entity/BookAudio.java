package com.hzau.feidian.hzauaudiobook.dao.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookAudio {

    private Long id;

    private String name;

    private String path;

    private Long bookId;

    public BookAudio(String path, Long bookId) {
        this.path = path;
        this.bookId = bookId;
    }
}
