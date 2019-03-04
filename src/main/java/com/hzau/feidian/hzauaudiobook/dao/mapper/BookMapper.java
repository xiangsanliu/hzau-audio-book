package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> selectAllBooks();

    int insertBook(@Param("book") Book book);

    int updateBook(@Param("book") Book book);

    int deleteOne(@Param("id") long id);

}
