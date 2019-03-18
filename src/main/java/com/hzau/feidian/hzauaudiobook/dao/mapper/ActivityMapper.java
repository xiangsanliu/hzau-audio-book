package com.hzau.feidian.hzauaudiobook.dao.mapper;

import com.hzau.feidian.hzauaudiobook.dao.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/17 11:56
 * @comment
 */

@Mapper
public interface ActivityMapper {

    int insert(@Param("activity") Activity activity);

    int update(@Param("activity") Activity activity);

    int delete(@Param("id") long id);

    List<Activity> selectAllActivities();

    Activity selectOne(@Param("id")long id);


}
