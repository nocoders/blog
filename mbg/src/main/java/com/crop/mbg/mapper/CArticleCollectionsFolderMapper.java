package com.crop.mbg.mapper;

import com.crop.mbg.model.CArticleCollectionsFolder;
import com.crop.mbg.model.CArticleCollectionsFolderExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleCollectionsFolderMapper {
    long countByExample(CArticleCollectionsFolderExample example);

    int deleteByExample(CArticleCollectionsFolderExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleCollectionsFolder record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleCollectionsFolder record);

    List<CArticleCollectionsFolder> selectByExample(CArticleCollectionsFolderExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticleCollectionsFolder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleCollectionsFolder record, @Param("example") CArticleCollectionsFolderExample example);

    int updateByExample(@Param("record") CArticleCollectionsFolder record, @Param("example") CArticleCollectionsFolderExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleCollectionsFolder record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleCollectionsFolder record);
}