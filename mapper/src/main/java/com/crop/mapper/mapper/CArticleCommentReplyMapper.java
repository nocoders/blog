package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleCommentReply;
import com.crop.mapper.model.CArticleCommentReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CArticleCommentReplyMapper {
    long countByExample(CArticleCommentReplyExample example);

    int deleteByExample(CArticleCommentReplyExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CArticleCommentReply record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CArticleCommentReply record);

    List<CArticleCommentReply> selectByExample(CArticleCommentReplyExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CArticleCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleCommentReply record, @Param("example") CArticleCommentReplyExample example);

    int updateByExample(@Param("record") CArticleCommentReply record, @Param("example") CArticleCommentReplyExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CArticleCommentReply record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CArticleCommentReply record);
}