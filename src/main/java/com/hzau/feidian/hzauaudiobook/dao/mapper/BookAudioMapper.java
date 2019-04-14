package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.BookAudio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookAudioMapper {

    int insert(@Param("bookAudio") BookAudio bookAudio);

    List<BookAudio> selectBookAudiosByBook(@Param("openid") String openid, @Param("bookId") long bookId);

    BookAudio selectOne(@Param("id") long id);

    int deleteOne(@Param("id") long id);

    int deleteByBook(@Param("bookId") long bookId);

    int incAmount(@Param("id") long id);

}
