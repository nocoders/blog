package com.crop.mbg.mapper;

import com.crop.mbg.model.CArticleNew;
import com.crop.mbg.model.CArticleNewExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleNewMapper {
    long countByExample(CArticleNewExample example);

    int deleteByExample(CArticleNewExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleNew record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleNew record);

    List<CArticleNew> selectByExample(CArticleNewExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticleNew selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleNew record, @Param("example") CArticleNewExample example);

    int updateByExample(@Param("record") CArticleNew record, @Param("example") CArticleNewExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleNew record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleNew record);
}