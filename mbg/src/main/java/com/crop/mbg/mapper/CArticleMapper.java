package com.crop.mbg.mapper;

import com.crop.mbg.model.CArticle;
import com.crop.mbg.model.CArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleMapper {
    long countByExample(CArticleExample example);

    int deleteByExample(CArticleExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticle record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticle record);

    List<CArticle> selectByExample(CArticleExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticle record, @Param("example") CArticleExample example);

    int updateByExample(@Param("record") CArticle record, @Param("example") CArticleExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticle record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticle record);
}