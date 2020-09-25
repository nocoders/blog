package com.crop.mbg.mapper;

import com.crop.mbg.model.CArticleContent;
import com.crop.mbg.model.CArticleContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleContentMapper {
    long countByExample(CArticleContentExample example);

    int deleteByExample(CArticleContentExample example);

    /**
     * 
     * @param articleId 删除数据主键id
     */
    int deleteByPrimaryKey(Long articleId);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleContent record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleContent record);

    List<CArticleContent> selectByExampleWithBLOBs(CArticleContentExample example);

    List<CArticleContent> selectByExample(CArticleContentExample example);

    /**
     * 根据主键id查询
     * @param articleId 删除数据主键id
     */
    CArticleContent selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("record") CArticleContent record, @Param("example") CArticleContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CArticleContent record, @Param("example") CArticleContentExample example);

    int updateByExample(@Param("record") CArticleContent record, @Param("example") CArticleContentExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleContent record);

    int updateByPrimaryKeyWithBLOBs(CArticleContent record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleContent record);
}