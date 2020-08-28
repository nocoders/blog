package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleCommentReply;
import com.crop.mapper.model.CArticleCommentReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CArticleCommentReplyMapper {
    long countByExample(CArticleCommentReplyExample example);

    int deleteByExample(CArticleCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CArticleCommentReply record);

    int insertSelective(CArticleCommentReply record);

    List<CArticleCommentReply> selectByExample(CArticleCommentReplyExample example);

    CArticleCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleCommentReply record, @Param("example") CArticleCommentReplyExample example);

    int updateByExample(@Param("record") CArticleCommentReply record, @Param("example") CArticleCommentReplyExample example);

    int updateByPrimaryKeySelective(CArticleCommentReply record);

    int updateByPrimaryKey(CArticleCommentReply record);
}