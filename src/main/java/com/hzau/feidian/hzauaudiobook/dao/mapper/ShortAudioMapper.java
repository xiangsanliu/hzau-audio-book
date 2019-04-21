package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.ShortAudio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/18 15:53
 * @comment
 */

@Mapper
public interface ShortAudioMapper {

    int insert(@Param("audio") ShortAudio audio);

    int updateApprove(@Param("id") long id, @Param("approved") boolean approved, @Param("reason") String reason);

    List<ShortAudio> selectAll();

    List<ShortAudio> selectAllApproved(@Param("openid") String openid);

    List<ShortAudio> selectMy(@Param("openid") String openid);

    List<ShortAudio> selectApprovedByAct(@Param("openid") String openid, @Param("id") long id);

    List<ShortAudio> search(@Param("openid") String openid, @Param("keyword") String keyword);

    int incAmount(@Param("id") long id);

}
