package com.crop.mapper.mapper;

import com.crop.mapper.model.CActicle;
import com.crop.mapper.model.CActicleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CActicleMapper {
    long countByExample(CActicleExample example);

    int deleteByExample(CActicleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CActicle record);

    int insertSelective(CActicle record);

    List<CActicle> selectByExample(CActicleExample example);

    CActicle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CActicle record, @Param("example") CActicleExample example);

    int updateByExample(@Param("record") CActicle record, @Param("example") CActicleExample example);

    int updateByPrimaryKeySelective(CActicle record);

    int updateByPrimaryKey(CActicle record);
}