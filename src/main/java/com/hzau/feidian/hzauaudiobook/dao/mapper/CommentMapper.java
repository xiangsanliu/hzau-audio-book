package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/21 16:24
 * @comment
 */

@Mapper
public interface CommentMapper {

    int insertIntoBookAudio(@Param("comment") Comment comment);

    int insertIntoShortAudio(@Param("comment") Comment comment);

    List<Comment> selectByBook(@Param("id") long id);

    List<Comment> selectByShort(@Param("id") long id);

    int incBookThumb(@Param("id") long id);

    int incShortThumb(@Param("id") long id);

}
