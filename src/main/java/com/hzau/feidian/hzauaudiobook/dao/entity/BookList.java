package com.hzau.feidian.hzauaudiobook.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookList {

    private Long id;

    private String name;

    private List<Book> books;

}
