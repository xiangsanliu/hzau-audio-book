package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> selectAllBooks();

    Book selectById(@Param("id") long id);

    List<Book> search(@Param("keyword") String keyword);

    int insertBook(@Param("book") Book book);

    int updateBook(@Param("book") Book book);

    int updatePoster(@Param("bookId") long bookId, @Param("posterUpdated") boolean posterUpdated);

    int deleteOne(@Param("id") long id);

}
