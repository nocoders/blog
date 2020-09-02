package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleLikes;
import com.crop.mapper.model.CArticleLikesExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleLikesMapper {
    long countByExample(CArticleLikesExample example);

    int deleteByExample(CArticleLikesExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleLikes record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleLikes record);

    List<CArticleLikes> selectByExample(CArticleLikesExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticleLikes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleLikes record, @Param("example") CArticleLikesExample example);

    int updateByExample(@Param("record") CArticleLikes record, @Param("example") CArticleLikesExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleLikes record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleLikes record);
}