package com.crop.mbg.mapper;

import com.crop.mbg.model.CArticleCollections;
import com.crop.mbg.model.CArticleCollectionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleCollectionsMapper {
    long countByExample(CArticleCollectionsExample example);

    int deleteByExample(CArticleCollectionsExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleCollections record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleCollections record);

    List<CArticleCollections> selectByExample(CArticleCollectionsExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticleCollections selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleCollections record, @Param("example") CArticleCollectionsExample example);

    int updateByExample(@Param("record") CArticleCollections record, @Param("example") CArticleCollectionsExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleCollections record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleCollections record);
}