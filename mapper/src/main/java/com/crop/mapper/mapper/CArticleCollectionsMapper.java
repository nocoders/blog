package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleCollections;
import com.crop.mapper.model.CArticleCollectionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CArticleCollectionsMapper {
    long countByExample(CArticleCollectionsExample example);

    int deleteByExample(CArticleCollectionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CArticleCollections record);

    int insertSelective(CArticleCollections record);

    List<CArticleCollections> selectByExample(CArticleCollectionsExample example);

    CArticleCollections selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleCollections record, @Param("example") CArticleCollectionsExample example);

    int updateByExample(@Param("record") CArticleCollections record, @Param("example") CArticleCollectionsExample example);

    int updateByPrimaryKeySelective(CArticleCollections record);

    int updateByPrimaryKey(CArticleCollections record);
}