package com.back.dao;

import com.back.entity.Resourse;
import com.back.entity.ResourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourseMapper {
    int countByExample(ResourseExample example);

    int deleteByExample(ResourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(Resourse record);

    int insertSelective(Resourse record);

    List<Resourse> selectByExample(ResourseExample example);

    Resourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Resourse record, @Param("example") ResourseExample example);

    int updateByExample(@Param("record") Resourse record, @Param("example") ResourseExample example);

    int updateByPrimaryKeySelective(Resourse record);

    int updateByPrimaryKey(Resourse record);
}