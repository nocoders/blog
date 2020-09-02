package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleComments;
import com.crop.mapper.model.CArticleCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleCommentsMapper {
    long countByExample(CArticleCommentsExample example);

    int deleteByExample(CArticleCommentsExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleComments record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleComments record);

    List<CArticleComments> selectByExample(CArticleCommentsExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticleComments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleComments record, @Param("example") CArticleCommentsExample example);

    int updateByExample(@Param("record") CArticleComments record, @Param("example") CArticleCommentsExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleComments record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleComments record);
}