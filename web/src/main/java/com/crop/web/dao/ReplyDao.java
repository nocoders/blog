package com.crop.web.dao;

import com.crop.mbg.mapper.CArticleCommentReplyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao extends CArticleCommentReplyMapper {
    String getReplyIdTree(Long id);

    int batchDelete(String ids);
}
