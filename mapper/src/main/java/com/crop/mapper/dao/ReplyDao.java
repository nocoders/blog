package com.crop.mapper.dao;

import com.crop.mapper.mapper.CArticleCommentReplyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao extends CArticleCommentReplyMapper {
    String getReplyIdTree(Long id);

    int batchDelete(String ids);
}
