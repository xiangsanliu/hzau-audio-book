package com.hzau.feidian.hzauaudiobook.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 项三六
 * @time 2019/4/14 13:00
 * @comment
 */
@Mapper
public interface ThumbMapper {

    int insertTBA(@Param("openid") String openid, @Param("bookAudioId") long bookAudioId);

    int insertTSA(@Param("openid") String openid, @Param("shortAudioId") long shortAudioId);

    int insertTCBA(@Param("openid") String openid, @Param("commentBookAudioId") long commentBookAudioId);

    int insertTCSA(@Param("openid") String openid, @Param("commentShortAudioId") long commentShortAudioId);

    int deleteTBA(@Param("openid") String openid, @Param("bookAudioId") long bookAudioId);

    int deleteTSA(@Param("openid") String openid, @Param("shortAudioId") long shortAudioId);

    int deleteTCBA(@Param("openid") String openid, @Param("commentBookAudioId") long commentBookAudioId);

    int deleteTCSA(@Param("openid") String openid, @Param("commentShortAudioId") long commentShortAudioId);

}
