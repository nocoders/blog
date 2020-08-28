package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleLikes;
import com.crop.mapper.model.CArticleLikesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CArticleLikesMapper {
    long countByExample(CArticleLikesExample example);

    int deleteByExample(CArticleLikesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CArticleLikes record);

    int insertSelective(CArticleLikes record);

    List<CArticleLikes> selectByExample(CArticleLikesExample example);

    CArticleLikes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleLikes record, @Param("example") CArticleLikesExample example);

    int updateByExample(@Param("record") CArticleLikes record, @Param("example") CArticleLikesExample example);

    int updateByPrimaryKeySelective(CArticleLikes record);

    int updateByPrimaryKey(CArticleLikes record);
}