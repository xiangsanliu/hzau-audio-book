package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.Book;
import com.hzau.feidian.hzauaudiobook.dao.entity.BookList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookListMapper {

    int insert(@Param("bookList") BookList bookList);

    int insertBook(@Param("bookListId") long bookListId, @Param("bookId") long bookId);

    int update(@Param("bookList") BookList bookList);

    List<BookList> selectAllBookLists();

    List<Book> selectBooksByBookList(@Param("bookListId") long bookListId);

    int deleteBookList(@Param("id") long id);

    int deleteBook(@Param("bookListId") long bookListId, @Param("bookId") long bookId);

    int deleteBookListBook(@Param("bookListId") long bookListId);

}
