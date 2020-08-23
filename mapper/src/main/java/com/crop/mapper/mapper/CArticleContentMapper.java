package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleContent;
import com.crop.mapper.model.CArticleContentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CArticleContentMapper {
    long countByExample(CArticleContentExample example);

    int deleteByExample(CArticleContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CArticleContent record);

    int insertSelective(CArticleContent record);

    List<CArticleContent> selectByExampleWithBLOBs(CArticleContentExample example);

    List<CArticleContent> selectByExample(CArticleContentExample example);

    CArticleContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleContent record, @Param("example") CArticleContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CArticleContent record, @Param("example") CArticleContentExample example);

    int updateByExample(@Param("record") CArticleContent record, @Param("example") CArticleContentExample example);

    int updateByPrimaryKeySelective(CArticleContent record);

    int updateByPrimaryKeyWithBLOBs(CArticleContent record);

    int updateByPrimaryKey(CArticleContent record);
}