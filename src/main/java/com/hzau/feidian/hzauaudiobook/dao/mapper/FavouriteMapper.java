package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.FavouriteBookAudio;
import com.hzau.feidian.hzauaudiobook.dao.entity.FavouriteShortAudio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/27 21:54
 * @comment
 */

@Mapper
public interface FavouriteMapper {

    int insertFBA(@Param("openid") String openid, @Param("bookAudioId") long bookAudioId);

    int insertFSA(@Param("openid") String openid, @Param("shortAudioId") long shortAudioId);

    int deleteFBA(@Param("openid") String openid, @Param("bookAudioId") long bookAudioId);

    int deleteFSA(@Param("openid") String openid, @Param("shortAudioId") long shortAudioId);

    List<FavouriteBookAudio> selectFBA(@Param("openid") String openid);

    List<FavouriteShortAudio> selectFSA(@Param("openid") String openid);


}
